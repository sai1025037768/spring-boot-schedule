var legendData = new Map();
var seriesData = [];
var list = [];

legendData.set("非常健康", '非常健康       健康指数≥80        148个');
legendData.set("健康", '健康         60≤健康指数＜80       25个');
legendData.set("一般", '一般         40≤健康指数＜60       20个');
legendData.set("较差", '较差         20≤健康指数＜40       18个');
legendData.set("极差", '极差              健康指数<20         12个');
var selfChartdata = [{value: 148, name: '非常健康', itemStyle: {color: '#7ECF51'}},
    {value: 25, name: '健康', itemStyle: {color: '#61A5E8'}},
    {value: 20, name: '一般', itemStyle: {color: '#FFFF00'}},
    {value: 18, name: '较差', itemStyle: {color: '#FFA500'}},
    {value: 12, name: '极差', itemStyle: {color: '#E16757'}},

    ];
console.log(selfChartdata);
option = {

    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        textStyle: {
            fontSize: 30
        },
        left: '10%',
        bottom: '20%',
        formatter: function (name) {
            if(legendData.get(name)){
                return legendData.get(name);
            };
            return name;
        }
    },
    series: [
        {
            name: '系统健康状态统计',
            type: 'pie',
            radius: '50%',
            center: ['50%', '30%'],
            data: selfChartdata,

            label: {
                color: 'rgba(255, 255, 255, 1.0)',
                fontSize: 30,
                show: false
            },
            labelLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
            },
            itemStyle: {
                color: '#c23531',
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};