var url = 'http://10.100.245.102:8088/service/serviceinterface/search/run.action?interfaceId=9dfd7b6b5428bbb52f1c387400253019&token=1567ff2cb7e56c3c7b1a0738e4a15ef5';
var legendData = [];
var seriesData = [];
var selfChartdata = [];
var list = [];
var selfZxlx = '#ZXLX#';
var selfzlxmc = '#ZXLXMC#'
$.ajax({
    url: url,
    type: "get",
    async: false,
    cache: true,
    dataType: "json",
    //请求成功
    success: function (result) {
        var data = result.data;
        seriesData = result.data;
        console.log(result);
        var typeArr = ["办公系统","业务系统","门户网站","非门户网站","其他"];
        var dataMap = new Map();
        for(var i=0;i<seriesData.length;i++){
            var item = seriesData[i];
            dataMap.set(item.DMMC,item);
        }

        selfChartdata = [{value:dataMap.get("办公系统").SL, name:'办公系统', itemStyle: {color: '#61A5E8'}},
            {value:dataMap.get("业务系统").SL, name:'业务系统', itemStyle: {color: '#66CC00'}},
            {value:dataMap.get("门户网站").SL, name:'门户网站', itemStyle: {color: '#DFDF00'}},
            {value:dataMap.get("其他网站").SL, name:'其他网站', itemStyle: {color: '#DF9000'}},
            {value:dataMap.get("其他").SL, name:'其他', itemStyle: {color: '#C55A4C'}}];
        legendData.push('办公系统' + '  '+dataMap.get("办公系统").SL +'个'+'  '+dataMap.get("办公系统").BFB  + '%');
        legendData.push('业务系统' + '  '+dataMap.get("业务系统").SL +'个'+'  '+dataMap.get("业务系统").BFB  + '%');
        legendData.push('门户网站' + '  '+dataMap.get("门户网站").SL +'个'+'  '+dataMap.get("门户网站").BFB  + '%') ;
        legendData.push('其他网站' + '  '+dataMap.get("其他网站").SL +'个'+'  '+dataMap.get("其他网站").BFB  + '%');
        legendData.push('其他' + '  '+dataMap.get("其他").SL +'个'+'  '+dataMap.get("其他").BFB + '%');

        option = {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                textStyle:{
                    fontSize:30
                },
                left: '50%',
                top:'30%',
                formatter: function (name) {
                    for(var p =0;p<legendData.length;p++){
                        var pp = legendData[p];
                        if(pp.indexOf(name) != -1){
                            return pp;
                        }
                    }
                    return name;
                }
            },
            series: [
                {
                    name: selfzlxmc,
                    type: 'pie',
                    radius: ['50%', '70%'],
                    center: ['30%', '50%'],
                    avoidLabelOverlap: false,
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: false,
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data:selfChartdata
                }
            ]
        };


    },
    //请求失败，包含具体的错误信息
    error: function (e) {
        console.log(e.status);
        console.log(e.responseText);
    }
});