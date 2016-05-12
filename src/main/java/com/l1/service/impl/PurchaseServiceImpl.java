package com.l1.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.l1.dao.InventoryDao;
import com.l1.dao.PurchaseDao;
import com.l1.dao.PurchaseDtlDao;
import com.l1.dao.SkuDao;
import com.l1.entity.Inventory;
import com.l1.entity.Purchase;
import com.l1.entity.PurchaseDtl;
import com.l1.entity.Sku;
import com.l1.service.PurchaseService;
import com.l1.service.SeqService;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
  @Resource
  private PurchaseDao purchaseDao;

  @Resource
  private PurchaseDtlDao purchaseDtlDao;

  @Resource
  private InventoryDao inventoryDao;

  @Resource
  private SkuDao skuDao;
  
  @Resource
  private SeqService seqService;

  @Override
  public List<Purchase> find(Map<String, Object> map) {
    return purchaseDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return purchaseDao.getTotal(map);
  }

  @Override
  public Integer add(Purchase purchase) {
    return purchaseDao.add(purchase);
  }

  @Override
  public Integer update(Purchase purchase) {
    return purchaseDao.update(purchase);
  }

  @Override
  public Integer deleteById(Integer id) {
    return purchaseDao.deleteById(id);
  }

  @Override
  public Integer delete(Integer[] ids) {
    int count = purchaseDao.delete(ids);

    for (Integer id : ids) {
      purchaseDtlDao.deleteByPuchaseId(id);
    }

    return count;
  }

  @Override
  public List<Purchase> findByIds(String ids) {
    return purchaseDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return purchaseDao.findNamesByIds(ids);
  }

  @Override
  public Purchase findById(Integer id) {
    return purchaseDao.findById(id);
  }

  @Override
  public int save(Purchase purchase) {
    return purchaseDao.save(purchase);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public int savePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> dtls) {
    String billNo = seqService.next("CG");
    purchase.setBillNo(billNo);
    
    int id = this.save(purchase);
    if (dtls != null && dtls.size() > 0) {
      for (PurchaseDtl dtl : dtls) {
        dtl.setId(purchase.getId());
      }
      purchaseDtlDao.batchSave(dtls);
    }

    return id;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public int updatePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> dtls) {
    if (purchase.getBillDate()==null || purchase.getBillNo().trim().length()<=0) {
      String billNo = seqService.next("CG");
      purchase.setBillNo(billNo);
    }
    
    int id = this.update(purchase);
    purchaseDtlDao.deleteByPuchaseId(purchase.getId());

    if (dtls != null && dtls.size() > 0) {
      for (PurchaseDtl dtl : dtls) {
        dtl.setId(purchase.getId());
      }
      purchaseDtlDao.batchSave(dtls);
    }

    return id;
  }

  @Override
  public int finish(Integer[] ids,Integer[] warehouseIds) {
    int count = 0;
    try {
      count = purchaseDao.finish(ids);
      if (count > 0) {
        // 库存维护
        for (int i=0;i<ids.length;i++) {
          int id = ids[i];
          int warehouseId = ids[i];
          
          List<PurchaseDtl> dtls = purchaseDtlDao.findByPurchaseId(id);
          for (PurchaseDtl purchaseDtl : dtls) {
            Map<String, Object> params= new HashMap<String, Object>();
            params.put("skuId", purchaseDtl.getSkuId());
            params.put("warehouseId", warehouseId);
            
            Inventory inventory = inventoryDao.findBySkuAndWarehouse(params);
            if (inventory != null) {
              inventory.setAmount(purchaseDtl.getItemAmount() + inventory.getAmount());
              inventoryDao.update(inventory);
            } else {
              inventory = new Inventory();
              inventory.setSkuId(purchaseDtl.getSkuId());
              inventory.setWarehouseId(warehouseId);
              inventory.setAmount(purchaseDtl.getItemAmount());

              Sku sku = skuDao.findById(purchaseDtl.getSkuId());
              if (sku != null) {
                inventory.setColorId(sku.getColorId());
                inventory.setSizeId(sku.getSizeDtlId());
                inventory.setStyle(sku.getItemName());
              }

              inventoryDao.save(inventory);
            }
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

    return count;
  }

  @Override
  public int unfinish(Integer[] ids,Integer[] warehouseIds) {
    int count = purchaseDao.unfinish(ids);
    if (count > 0) {
      // 库存维护
      for (int i=0;i<ids.length;i++) {
        int id = ids[i];
        int warehouseId = ids[i];
        
        List<PurchaseDtl> dtls = purchaseDtlDao.findByPurchaseId(id);
        for (PurchaseDtl purchaseDtl : dtls) {
          Map<String, Object> params= new HashMap<String, Object>();
          params.put("skuId", purchaseDtl.getSkuId());
          params.put("warehouseId", warehouseId);
          
          Inventory inventory = inventoryDao.findBySkuAndWarehouse(params);
          if (inventory != null) {
            boolean can = inventory.getAmount() >= purchaseDtl.getItemAmount();
            if (!can) {
              throw new RuntimeException("库存不够!!");
            }

            inventory.setAmount(inventory.getAmount() - purchaseDtl.getItemAmount());
            inventoryDao.update(inventory);
          }
        }
      }
    }

    return count;
  }

}
