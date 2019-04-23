/**
*echarts 工具类
*echartsId:echarts图对象id
*setOptions:echarts设置参数
*	1.chartTitle:echarts标题（默认无）
*	2.tickAmount:y轴等分数量（默认5等分）
*	3.chartType:echarts类型（spline-曲线【默认】,line-线,area-面积,column-柱状,pie-饼图,gauge-仪表图）
*	4.chartInverted:是否横纵互换（默认否）
*	//5.seriesStep:'left' 或 'center'、'right'，表示转折位置（默认无）
*	6.yAxisTitle:y轴标题（默认无）
*	7.colorType:颜色组（默认清新自然,qxzr-清新自然,jjmk-简洁明快,lsjm-绿色静谧,sygd-素雅古典,lcty-亮彩跳跃,ldhp-灵动活波,fhyh-粉红诱惑,hsrq-红色热情,slhd-深蓝海底,xdjy-现代简约）
*	8.colors:自定义颜色组
*	9.columnInside:柱形内显示数据（默认否）
*	10.data:数据（a.字符串【x1,x2,x3,x4;xxx,1,2,3,4;xxx2,1,2,3,4】,
*				b.数组【[['x1','x2','x3','x4'],[{name:'xxx',data:[1,2,3,4]},{name:'xxx2',data:[1,2,3,4]}]】）
*	11.xData:x轴数据（a.字符串【x1,x2,x3,x4】,b.数组【['x1','x2','x3','x4']】）
*	12.yData:x轴数据（a.字符串【xxx,1,2,3,4;xxx2,1,2,3,4】,b.数组【[{name:'xxx',data:[1,2,3,4]},{name:'xxx2',data:[1,2,3,4]}]】）
*	13.defaultShowNum:多数据组时默认显示数量
*	14.tooltip:标签设置（默认共享标签）
*	15.innerSize:饼图内部大小
*	16.stacking:面积堆积
*	17.colorByPoint:多颜色柱状图
*	18.showDataLabels:是否显示数据标签
*	19.showInLegend:显示饼图图例
*	20.yAxisMin:y轴最小值
*	21.yAxisMax:y轴最大值
*	22.gridTop:图表顶部距离
*	23.gridLeft:图表左边距离
*	24.gridRight:图表右边距离
*	25.gridBottom:图表底部距离
*	26.xAllShow:x轴是否全显示
*	27.xRotate:x轴是否倾斜
*	28.legendY:默认底部（'bottom'，'top'）
*/
var myEChartsIdArr=[];
var myEChartsDomArr=[];
var EchartsUtils=function(echartsId,setOptions){
	if(typeof(echartsId)=='undefined'){
        return;
    }
	var myChart;
	var chartIndex=$.inArray(echartsId,myEChartsIdArr);
	if(chartIndex>-1){
		myChart=myEChartsDomArr[chartIndex];
		myChart.dispose();
		myEChartsIdArr.splice(chartIndex,1);
		myEChartsDomArr.splice(chartIndex,1);
	}
	myChart = echarts.init(document.getElementById(echartsId));
	myEChartsIdArr.push(echartsId);
	myEChartsDomArr.push(myChart);
	//判断是否数字，如是，转数字
	var myParseNumber=function(str){
		var val=Number(str);
		if(isNaN(val)){
			return str;
		}else{
			return val;
		}
	};
	//字符串自动转json数组
	var getJSONData=function(str){
		var strs=str.split(";");
		var arr=[];
		var data=[];
		var chartTypeObj={'spline':'spline','line':'line','column':'bar'};
		for(var i=0;i<strs.length;i++){
			strs[i]=strs[i].split(",").map(myParseNumber);
			if(i==0&&strs[i].length>1&&typeof(strs[i][1])!='number'){
				arr.push(strs[i]);
			}else{
				if(typeof(strs[i][0])!='number'){
					var temp={data:[]};
					var startIndex=0;
					if(chartTypeObj[strs[i][startIndex]]){
						temp.type=chartTypeObj[strs[i][startIndex]];
						startIndex++;
					}
					temp.name=strs[i][startIndex];
					startIndex++;
					for(var j=startIndex;j<strs[i].length;j++){
						temp.data[j-startIndex]=strs[i][j];
					}
					if(i>defaultShowNum){
						temp.visible=false;
					}
					data.push(temp);
				}else{
					arr.push(strs[i]);
				}
			}
		}
		if(data.length!=0){
			arr.push(data);
		}
		return arr;
	};
	//字符串转饼图数据
	var getPieData=function(str){
		var strs=str.split(";");
		var arr=[];
		var data;
		for(var i=0;i<strs.length;i++){
			data={data:[]};
			strs[i]=strs[i].split(",").map(myParseNumber);
			var startIndex=0;
			if(strs[i].length%2==1){
				data.name=strs[i][0];
				startIndex=1;
			}
			for(var j=startIndex;j<strs[i].length-1;j++){
				data.data.push({name:strs[i][j],value:strs[i][++j]});
			}
			arr.push(data);
		}
		return arr;
	};
	//颜色分组
	var colorType={};
	//颜色组：清新自然
	colorType.qxzr=['#F87A8B','#D4E28A','#83D3EC','#FBC892','#8E8EBD','#F78B72'];
	//颜色组：简洁明快
	colorType.jjmk=['#31CE54','#FFA23E','#129FEA','#FBD841','#B361FB','#F65646'];
	//颜色组：绿色静谧
	colorType.lsjm=['#5CBEA2','#E9E189','#91C448','#B7DDC0','#FEE57C','#C5DE91'];
	//颜色组：素雅古典
	colorType.sygd=['#5DD4FE','#FFAFAB','#6E59FB','#A878C9','#FF5796','#369A25'];
	//颜色组：亮彩跳跃
	colorType.lcty=['#8352FB','#FEE65E','#A3FF75','#4AF8FF','#F6A139','#FF5640'];
	//颜色组：灵动活波
	colorType.ldhp=['#1BAAE9','#BCDB35','#F55D73','#EFDD31','#55C5A4','#746CDB'];
	//颜色组：粉红诱惑
	colorType.fhyh=['#B056D2','#EB3875','#E59595','#C180EC','#BB71AD','#BE76D8'];
	//颜色组：红色热情
	colorType.hsrq=['#E12E6B','#F4AE2C','#AA2CD9','#E9508A','#EC6D22','#D45A8B'];
	//颜色组：深蓝海底
	colorType.slhd=['#38A9E0','#4DE4C4','#A280D6','#4B7BCB','#14A2B4','#4D99D2'];
	//颜色组：现代简约
	colorType.xdjy=['#5187BA','#DF585C','#F0953A','#95C7E4','#F6BD8A','#5FA755'];
	var colormore=[['qxzr','lsjm'],['jjmk','lcty'],['sygd','ldhp'],['fhyh','hsrq'],['slhd','xdjy']];
	for(var i=0;i<colormore.length;i++){
		var tempColor_0=colorType[colormore[i][0]];
		var tempColor_1=colorType[colormore[i][1]];
		colorType[colormore[i][0]]=tempColor_0.concat(tempColor_1);
		colorType[colormore[i][1]]=tempColor_1.concat(tempColor_0);
	}
	//默认颜色组
	var colors=colorType.qxzr;
	//y轴默认等分数量
	var tickAmount=5;
	//echarts标题
	var chartTitle='';
	//多数据组时默认显示数量
	var defaultShowNum=3;
	//默认类型：曲线
	var chartType='spline';
	//是否横纵互换
	var chartInverted=false;
	//y轴标题
	var yAxisTitle=null;
	//x轴数据
	var xDataArr=[''];
	var xShowDataArrLen=0;
	//y轴数据
	var yDataArr=[{name: ' ',data: []}];
	//默认标签共享
	var tooltip={trigger: 'axis'};
	//面积堆积
	var stacking=null;
	var lineStacking=true;
	//多颜色柱状图
	var colorByPoint=null;
	//是否显示数据标签
	var showDataLabels=true;
	//环形饼图内部大小
	var innerSize=null;
	var outerSize=100;
	//环形饼图图例
	var showInLegend;
	//'left' 或 'center'、'right'，表示转折位置
// 	var seriesStep=null;
	//柱状图是否标签内显示数据
	var columnInside=false;
	//y轴最小值
	var yAxisMin=null;
	//y轴最大值
	var yAxisMax=null;
	//图表顶部距离
	var gridTop=30;
	//图表左边距离
	var gridLeft='12%';
	//图表右边距离
	var gridRight='6%';
	//图表底部距离
	var gridBottom=60;
	var showPieMain=false;
	//x轴是否全显示
	var xAllShow=false;
	//x轴是否倾斜
	var xRotate=false;
	var legendY='bottom';
	var titlePosition="top";
	var legendData=[];
	var legendSelected={};
	//echarts参数赋值
    if(typeof(setOptions)!='undefined'){
        if(setOptions.chartTitle){
			chartTitle=setOptions.chartTitle;
			gridTop=40;
		}
		if(setOptions.tickAmount){
			tickAmount=setOptions.tickAmount;
		}
		if(setOptions.chartType){
			chartType=setOptions.chartType;
		}
		if(chartType=='pie'){
			showInLegend=false;
		}else{
			showInLegend=true;
		}
		if(setOptions.innerSize){
			innerSize=setOptions.innerSize;
		}
		if(setOptions.outerSize){
			outerSize=(setOptions.outerSize+"").replace('%','');
		}
		if(typeof(setOptions.showInLegend)!='undefined'){
			showInLegend=setOptions.showInLegend;
		}
		if(setOptions.seriesStep){
			seriesStep=setOptions.seriesStep;
		}
		if(chartType=="pie"&&setOptions.titlePosition=="center"){
			titlePosition=setOptions.titlePosition;
		}
		if(chartType=="pie"&&setOptions.showPieMain==true){
			showPieMain=setOptions.showPieMain;
		}
		if(setOptions.yAxisTitle){
			yAxisTitle=setOptions.yAxisTitle;
			var tempYAxisTitleArr=[];
			if(yAxisTitle.indexOf(";")>-1){
				tempYAxisTitleArr=yAxisTitle.split(";");
				for(var i=0;i<tempYAxisTitleArr.length;i++){
					if(tempYAxisTitleArr[i].indexOf(",")>-1){
						tempYAxisTitleArr[i]=tempYAxisTitleArr[i].split(",");
						if(tempYAxisTitleArr[i].length>2){
							tempYAxisTitleArr[i][2]=tempYAxisTitleArr[i][2].split("-");
						}
					}else{
						tempYAxisTitleArr[i]=[tempYAxisTitleArr[i],tempYAxisTitleArr[i]];
					}
				}
			}else{
				if(yAxisTitle.indexOf(",")>-1){
					tempYAxisTitleArr=[yAxisTitle.split(",")];
					if(tempYAxisTitleArr[0].length>2){
						tempYAxisTitleArr[0][2]=tempYAxisTitleArr[0][2].split("-");
					}
				}else{
					tempYAxisTitleArr=[[yAxisTitle,yAxisTitle]];
				}
			}
			yAxisTitle=tempYAxisTitleArr;
		}
		function getYAxisIndex(xAxisIndex){
			var yAxisIndex=0;
			if(yAxisTitle!=null){
				for(var i=0;i<yAxisTitle.length;i++){
					if(yAxisTitle[i].length>2&&$.inArray(xAxisIndex+'',yAxisTitle[i][2])>-1){
						yAxisIndex=i;
						break;
					}
				}
			}
			return yAxisIndex;
		}
		if(setOptions.colorByPoint){
			colorByPoint=setOptions.colorByPoint;
		}
		if(setOptions.colorType){
			colors=colorType[setOptions.colorType];
		}
		if(setOptions.colors){
			if(typeof(setOptions.colors)=='string'){
				colors=setOptions.colors.split(",");
			}else{
				colors=setOptions.colors;
			}
		}
		if(typeof(setOptions.showDataLabels)!='undefined'){
			showDataLabels=setOptions.showDataLabels;
		}
		if(setOptions.stacking){
			stacking=setOptions.stacking;
			if(stacking=='normal'){
				columnInside=true;
			}
		}
		if(setOptions.chartInverted){
			chartInverted=setOptions.chartInverted;
		}
		if(typeof(setOptions.yAxisMin)!='undefined'){
			yAxisMin=setOptions.yAxisMin;
		}
		if(setOptions.yAxisMax){
			yAxisMax=setOptions.yAxisMax;
		}
		if(setOptions.gridTop){
			gridTop=setOptions.gridTop;
		}
		if(setOptions.gridLeft){
			gridLeft=setOptions.gridLeft;
		}else{
			if(chartInverted==true){
				gridLeft='16%';
				gridTop=40;
			}
		}
		if(setOptions.gridRight){
			gridRight=setOptions.gridRight;
		}
		if(setOptions.gridBottom){
			gridBottom=setOptions.gridBottom;
		}
		if(setOptions.xAllShow){
			xAllShow=setOptions.xAllShow;
		}
		if(setOptions.xRotate){
			xRotate=setOptions.xRotate;
		}
		if(setOptions.columnInside){
			columnInside=setOptions.columnInside;
		}
		if(setOptions.defaultShowNum){
			defaultShowNum=setOptions.defaultShowNum;
		}
		if(setOptions.legendY=='top'){
			legendY=setOptions.legendY;
		}
		if(setOptions.data){
			var data=setOptions.data;
			if(typeof(data)=='string'){
				if("gauge"==chartType){
					yDataArr=myParseNumber(data);
				}else if(chartType=='pie'){
					yDataArr=getPieData(data);
				}else{
					data=getJSONData(data,1);
					if(data.length==1){
						xDataArr=[data[0][0].name];
						yDataArr=data[0].slice(1);
					}else{
						xDataArr=data[0];
						yDataArr=data[1];
					}
				}
			}else{
				if("gauge"==chartType){
					yDataArr=data;
				}else if(chartType=='pie'){
					yDataArr=data;
				}else{
					xDataArr=data[0];
					for(var i=0;i<data[1].length;i++){
						if(i>defaultShowNum){
							data[1][i].visible=false;
						}
					}
					yDataArr=data[1];
				}
			}
		}
		if(setOptions.xData){
			if(typeof(setOptions.xData)=='string'){
				xDataArr=setOptions.xData.split(",");
			}else{
				xDataArr=setOptions.xData;
			}
		}
		if(setOptions.yData){
			if(typeof(setOptions.yData)=='string'){
				if(chartType=='pie'){
					yDataArr=getPieData(setOptions.yData);
				}else{
					yDataArr=getJSONData(setOptions.yData,0)[0];
				}
			}else{
				yDataArr=setOptions.yData;
			}
		}
		var oldSize=70*outerSize/100+'%';
		if("pie"==chartType&&innerSize==null&&yDataArr.length>1){
			innerSize=70*outerSize/100+'%';
		}
		for(var i=0;i<yDataArr.length;i++){
			if(yDataArr[i].visible!=false&&yDataArr[i].data.length>xShowDataArrLen){
				xShowDataArrLen=yDataArr[i].data.length;
			}
			if(chartType=='pie'){
				for(var j=0;j<yDataArr[i].data.length;j++){
					legendData.push(yDataArr[i].data[j].name);
				}
			}else{
				legendData.push(yDataArr[i].name);
				yDataArr[i].symbol=null;
				yDataArr[i].symbolSize= 8;
			}
			if(i>=defaultShowNum){
				legendSelected[yDataArr[i].name]=false;
			}
			if(typeof(yDataArr[i].type)=='undefined'){
				yDataArr[i].type='line';
				if('spline'==chartType||'areaspline'==chartType){
					yDataArr[i].smooth=true;
				}
			}else{
				lineStacking=false;
				if(yDataArr[i].type=='line'){
					yDataArr[i].smooth=false;
				}else if(yDataArr[i].type=='spline'){
					yDataArr[i].type='line';
					yDataArr[i].smooth=true;
				}
			}
			yDataArr[i].itemStyle={ 
				normal: {
					label : {
						show: showDataLabels,
						formatter: (chartType=='pie'?(yDataArr.length>1?'{b}':'{b}:{d}%'):'{c}'),
						textStyle: {
          					color: 'black'
            			},
            			position: columnInside?'inside':(chartInverted==true?'right':'top')
					}
				}
			};
			if('area'==chartType||'areaspline'==chartType){
				yDataArr[i].itemStyle.normal.areaStyle={type: 'default',opacity: 0.3};
			}
			if(stacking=='normal'){
				yDataArr[i].stack='总量';
			}
			if("column"==chartType){
				yDataArr[i].type='bar';
			}
			if("pie"==chartType){
				yDataArr[i].type='pie';
				var newInnerSize=null;
				if(innerSize!=null){
					newInnerSize=Number(innerSize.replace('%','')-20*i)*Number(oldSize.replace('%',''));
				}
				if(i==0){
					yDataArr[i].radius= [(newInnerSize/outerSize)+'%', oldSize];
					yDataArr[i].itemStyle.normal.label.show= showDataLabels;
					yDataArr[i].itemStyle.normal.labelLine={show: showDataLabels};
				}else{
					if(i==yDataArr.length-1){
						yDataArr[i].radius= ['0%', yDataArr[i-1].radius[0].replace('%','')*0.9+'%'];
					}else{
						yDataArr[i].radius= [(newInnerSize/outerSize)+'%', yDataArr[i-1].radius[0].replace('%','')*0.9+'%'];
					}
					yDataArr[i].itemStyle.normal.label.show= showDataLabels;
					yDataArr[i].itemStyle.normal.label.position= 'inner';
					yDataArr[i].itemStyle.normal.labelLine={show: false};
				}
			}
			if(chartType!='pie'&&chartType!='gauge'){
				var yAxisIndex=getYAxisIndex(i);
				if(yAxisIndex!=0){
					yDataArr[i].yAxisIndex=yAxisIndex;
				}
			}
			if(colorByPoint==true){
				yDataArr[i].itemStyle.normal.color= function(params) {
		          	if (params.dataIndex < 0) {
		          		return colors[colors.length - 1];
		          	} else {
		          		return colors[params.dataIndex%colors.length];
		          	}
		        };
			}
		}
		if(!lineStacking){
			for(var i=0;i<yDataArr.length;i++){
				if(yDataArr[i].type=='line'){
					delete yDataArr[i].stack;
				}
			}
		}
		if(setOptions.tooltip){
			tooltip=setOptions.tooltip;
			if(typeof(setOptions.tooltip.shared)=="undefined"){
				tooltip.trigger='axis';
			}else{
				tooltip.trigger=setOptions.tooltip.shared?'axis':null;
			}
		}
		var yDataValueSuffixs;
		if(yAxisTitle!=null){
			yDataValueSuffixs=[];
			for(var i=0;i<yAxisTitle.length;i++){
				yDataValueSuffixs.push(yAxisTitle[i][1]);
			}
		}else{
			yDataValueSuffixs=[''];
		}
		if("pie"==chartType){
			tooltip.trigger='item';
			tooltip.formatter= function(param) { 
				var tempValueSuffixs=yDataValueSuffixs[param.dataIndex%yDataValueSuffixs.length];
				return param.seriesName+'<br/><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + param.color + '"></span>'+param.name +' : '+param.value+tempValueSuffixs+'</br>';  
			};
		}else{
			tooltip.formatter= function(params) {  
				var res='';
				for(var i=0;i<params.length;i++){
					var tempValueSuffixs=yDataValueSuffixs[getYAxisIndex(i)%yDataValueSuffixs.length];
					if(i==0){
						res += params[i].axisValue+'<br/>';
					}
					res += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[i].color + '"></span>'+params[i].seriesName +' : '+params[i].value+tempValueSuffixs.replace("单位：","")+'</br>';  
				}
				return res;  
			};
		}
    }
	//echarts参数配置对象
	var options;
	if("gauge"==chartType){
		var value=yDataArr;
		options = {
		    title:{
		    	text:chartTitle,
		    	textStyle: {
		        	fontWeight: 'normal',              //标题颜色
		      	},
		    	x:'center'
		    },
		    tooltip : {
		        formatter: "{c}"+(yAxisTitle!=null?yAxisTitle[0][1]:'')
		    },
		    series : [
		        {
		           	startAngle: 180,
		            endAngle: 0,
		           	radius : '140%',
		            center: ['50%', '80%'],
		            name:'',
		            type:chartType,
		            splitNumber:5,       // 分割段数，默认为5
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: [], 
		                    width: 12
		                }
		            },
		            axisTick: {            // 坐标轴小标记
		                splitNumber: 1,   // 每份split细分多少段
		                length :12,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		          	max:yAxisMax,
		          	min:yAxisMin,
		            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                show: true,        // 默认显示，属性show控制显示与否
		                length :20,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'auto'
		                }
		            },
		            pointer : {
		                width : 10
		            },
		            title : {
		                show : false
		            },
		            detail : {
		                formatter:'{value}'+(yAxisTitle!=null?yAxisTitle[0][1]:''),
		              	offsetCenter: [0, '18%'],  
		              	textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    color: 'auto',
		                    fontWeight: 'bolder'
		                }
		            },
		            data:[{value: value}]
		        }
		    ]
		};
		options.series[0].splitNumber=yAxisMax-yAxisMin;
		for(var i=0;i<tickAmount;i++){
			options.series[0].axisLine.lineStyle.color.push([(i+1)/tickAmount,colors[i]]);
		}
	}else{
		var yAxisArr=[];
		if(yAxisTitle==null){
			yAxisArr.push({
		   		name : '',
				type : 'value',
				splitNumber: tickAmount,
				axisLabel :{  
				    interval:0,
	                formatter: '{value} ',
			     	textStyle: {
	               	}
				},
				axisLine :{
					show:false
				},
				axisTick:{
					show:false
				}
			});
			if(yAxisMax!=null){
				yAxisArr[0].max=yAxisMax;
			}
			if(yAxisMin!=null){
				yAxisArr[0].min=yAxisMin;
			}
		}else{
			if(yAxisMax!=null){
				yAxisMax=yAxisMax.split(",");
			}
			if(yAxisMin!=null){
				yAxisMin=yAxisMin.split(",");
			}
			for(var i=0;i<yAxisTitle.length;i++){
				yAxisArr.push({
			   		name : yAxisTitle[i][0],
					type : 'value',
					splitNumber: tickAmount,
					axisLabel :{  
						show:true,
					    interval:0,
		                formatter: '{value} '+(yAxisTitle[i][0]==yAxisTitle[i][1]?'':yAxisTitle[i][1]),
	        			textStyle: {
	        				color:'#666666'
	                   	}
					},
					axisLine :{
						lineStyle:{
							color:'rgb(204, 214, 235)'
						}
					}
				});
				if(yAxisMax!=null){
					yAxisArr[i].max=yAxisMax[i];
				}
				if(yAxisMin!=null){
					yAxisArr[i].min=yAxisMin[i];
				}
			}
		}
		options ={
		    title:{
		    	text:chartTitle,
		    	textStyle: {
		        	fontWeight: 'normal',              //标题颜色
		      	},
		    	x:'center'
		    },
		    tooltip: tooltip,
		    color: colors,
		    legend: {
		        data:legendData,
		        selected:legendSelected,
		        y : legendY,
	            itemGap: 10,               // 各个item之间的间隔，单位px，默认为10，
	                               // 横向布局时为水平间隔，纵向布局时为纵向间隔
		        itemWidth: 14,             // 图例图形宽度
		        itemHeight: 10,
		        padding: (setOptions.legendY=='top'?(chartTitle==''?[10,0,10,0]:[30,0,0,0]):((chartType=='pie'&&titlePosition=='center')?[0,0,5,0]:[0,0,20,0]))
		    },
		    grid: {
		    	top:parseInt(gridTop)+(legendY=='bottom'?0:20),
		        left: gridLeft,
		        right:gridRight,
		        bottom:parseInt(gridBottom)+(legendY=='bottom'?20:0)
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: "column"==chartType||!lineStacking?true:false,
		        data: xDataArr.slice(0,xShowDataArrLen),
		        axisLabel :{  
		        	interval:xAllShow?0:'auto',
		        	rotate:xRotate?40:0,
        			textStyle: {
        				color:'#666666'
                   	}
				},
				axisLine :{
					lineStyle:{
						color:'rgb(204, 214, 235)'
					}
				}
		    },
		   	yAxis : yAxisArr,
		    series: yDataArr
		};
		if(chartType=="pie"&&titlePosition=="center"){
			options.title.y='center';
		}
		if(chartType=='pie'&&showPieMain==true){
			if(innerSize!=null){
				innerSize=innerSize.replace("%","");
			}
			options.series[0].radius=[outerSize,innerSize];
			var totalVal=0;
			for(var i=0;i<options.series[0].data.length;i++){
				totalVal+=options.series[0].data[i].value;
			}
			options.series[0].animation=false;
			options.series[0].itemStyle={
				normal : {
                    label : {
                        formatter : function (params){
                            return (100 - params.value*100/totalVal).toFixed(1) + '%';
                        },
                        textStyle: {
                            baseline : 'top',
                          	color:'black',
                          	fontSize:18
                        }
                    }
                },
            };
			options.series[0].data[0].itemStyle={
                normal : {
                    label : {
                        show : true,
                        position : 'center',
                        formatter : '{b}',
                        textStyle: {
                          	color:"#aaa",
                            baseline : 'bottom'
                        }
                    },
                    labelLine : {
                        show : false
                    }
                }
            };
			for(var i=1;i<options.series[0].data.length;i++){
				options.series[0].data[i].itemStyle={
					 normal : {
                        color: '#eee',
                        label : {
                            show : true,
                            position : 'center'
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis: {
                        color: '#eee'
                    }
                };
			}
			delete options.grid;
			delete options.tooltip;
			delete options.title;
			delete options.legend;
			delete options.xAxis;
			delete options.yAxis;
		}
	}
	if(chartInverted==true){
		var tempAxis=options.xAxis;
		options.xAxis=options.yAxis;
		options.yAxis=tempAxis;
	}
	if("pie"==chartType){
		delete options.xAxis;
		delete options.yAxis;
	}
	if(showInLegend==true){
	}else{
		delete options.legend;
	}
	myChart.setOption(options,true);
	if(chartType!='pie'){
		myChart.on('legendselectchanged',function(params){
			options.legend.selected=params.selected;
			xShowDataArrLen=0;
			for(var i=0;i<yDataArr.length;i++){
				if(params.selected[yDataArr[i].name]==true&&yDataArr[i].data.length>xShowDataArrLen){
					xShowDataArrLen=yDataArr[i].data.length;
				}
			}
			if(chartInverted==true){
				options.yAxis.data=xDataArr.slice(0,xShowDataArrLen);
			}else{
				options.xAxis.data=xDataArr.slice(0,xShowDataArrLen);
			}
			myChart.setOption(options,true);
		});
	}
};