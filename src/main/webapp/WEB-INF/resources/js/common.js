



function getCurrentDateTime() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	return year + "-" + formatZero(month) + "-" + formatZero(day) + " "
			+ formatZero(hours) + ":" + formatZero(minutes) + ":"
			+ formatZero(seconds);
}

function getCurrentDate() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return year + "-" + formatZero(month) + "-" + formatZero(day);
}

function formatZero(n) {
	if (n >= 0 && n <= 9) {
		return "0" + n;
	} else {
		return n;
	}
}

function timeshow(){
    var date = 0;
    var type = 0;


    var d;
    var ev = 'system_time';
    var Y,M,D,W,H,I,S;
    var Week=['星期天','星期一','星期二','星期三','星期四','星期五','星期六'];
    if(date){
        d =new Date(date*1000);
    }else{
        d =new Date();
    }

    Y = d.getFullYear();
    M = fillZero(d.getMonth()+1);
    D = fillZero(d.getDate());
    W = Week[d.getDay()];
    H = fillZero(d.getHours());
    I = fillZero(d.getMinutes());
    S = fillZero(d.getSeconds());

    if(type && type==12){
        if(H<10 && H>=6){
            H='早晨:'+H;
        }else if(H>=10 && H<12){
            H='上午:'+fillZero(H);
        }else if(H>=12 && H<14){
            H='中午:'+H;
        }else if(H>=14 && H<18){
            H='下午:'+H;
        }else if(H>=18 && H<24){
            H='晚上:'+H;
        }else if(H==0){
            H='凌晨:00';
        }else if(H>0 && H<6 ){
            H='夜晚:'+H;
        }
    }
    var showData = Y+'年'+M+'月'+D+'日'+' '+H+':'+I+':'+S+'';

    $('#'+ev).html(showData);
    if(date){
        date++;
    }
    setTimeout(function(){timeshow()},1000);
}

function formatDatebox(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt = parseToDate(value);// 关键代码，将那个长字符串的日期值转换成正常的JS日期格式
	return dt.format("yyyy-MM-dd"); // 这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
}

/* 带时间 */
function formatDateBoxFull(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt = parseToDate(value);
	return dt.format("yyyy-MM-dd hh:mm:ss");
}

function parseToDate(value) {
	if (value == null || value == '') {
		return undefined;
	}

	console.log(typeof value);
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		if (!isNaN(value)) {
			dt = new Date(value);
		} else if (value.indexOf('/Date') > -1) {
			value = value.replace(/\/Date(−?\d+)\//, '$1');
			dt = new Date();
			dt.setTime(value);
		} else if (value.indexOf('/') > -1) {
			dt = new Date(Date.parse(value.replace(/-/g, '/')));
		} else {
			dt = new Date(value);
		}
	}
	return dt;
}

// 为Date类型拓展一个format方法，用于格式化日期
Date.prototype.format = function(format) // author: meizz
{
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

$.extend($.fn.datagrid.defaults.editors, {
	datebox : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			input.datebox(options);
			return input;
		},
		destroy : function(target) {
			$(target).datebox('destroy');
		},
		getValue : function(target) {
			return $(target).datebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datebox('setValue', formatDatebox(value));
		},
		resize : function(target, width) {
			$(target).datebox('resize', width);
		}
	},
	datetimebox : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			input.datetimebox(options);
			return input;
		},
		destroy : function(target) {
			$(target).datetimebox('destroy');
		},
		getValue : function(target) {
			return $(target).datetimebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datetimebox('setValue', formatDateBoxFull(value));
		},
		resize : function(target, width) {
			$(target).datetimebox('resize', width);
		}
	}
});