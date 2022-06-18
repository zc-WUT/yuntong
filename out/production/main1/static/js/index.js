// 折线图1模块制作
/*(function () {
    var myChart = echarts.init(document.querySelector(".line1 .chart"));
    /!*var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['1000', '1500', '2000', '3000', '3500', '4000', '5000', '6000', '7000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);*!/
    //异步
    /!* $.get('data/data.json').done(function (data) {
         console.log(data);
         myChart.setOption({
             tooltip:{},
             legend:{
                 data:['销量']
             },
             xAxis:{
               data:data.categories
             },
             yAxis:{},
             series:[{
                 name:'销量',
                 type:'bar',
                 data:data.data
             }]
         });

     })*!/

//异步二
            myChart.setOption({
                tooltip:{},
                legend:{
                    data:['销量']
                },
                xAxis:{
                    data:[]
                },
                yAxis:{},
                series:[{
                    name:'销量',
                    type:'bar',
                    data:[]
                }]
            });

            $.get('js/data.json').done(function (data) {
                //填入数据
                myChart.setOption({
                    xAxis:{
                        data:data.categories
                    },
                    series:[{
                        name:'销量',
                        data:data.data1
                    }]
                })
            })

    // 2.指定配置
  /!*  myChart.setOption({
        roam: true,
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            },
            // selectedMode: 'single'

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            boundaryGap: true,
            data: [],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                type: 'effectScatter ',
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [],
            },

            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: []
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: []
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: []
            },

        ]
    });

    $.get('data/data.json').done(function (data) {
        //填入数据
        myChart.setOption({
            xAxis: {
                data: data.categories
            },
            series: [{
                name: '起升钢丝绳1最大损伤量%',
                data: data.data1,

            },
                {
                    name: '起升钢丝绳2最大损伤量%',
                    data: data.data2
                },
                {
                    name: '起升钢丝绳3最大损伤量%',
                    data: data.data3
                },

                {
                    name: '起升钢丝绳4最大损伤量%',
                    data: data.data4
                }
            ]
        })
    })*!/
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line1 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['1000', '1500', '2000', '3000', '3500', '4000', '5000', '6000', '7000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line2 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['3000', '4500', '6000', '8700', '10000', '12000', '14000', '17000', '20000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line3 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['1000', '1500', '2000', '3000', '3500', '4000', '5000', '6000', '7000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line4 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['3000', '4500', '6000', '8700', '10000', '12000', '14000', '17000', '20000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line5 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['1000', '1500', '2000', '3000', '3500', '4000', '5000', '6000', '7000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/

/*(function () {
    var myChart = echarts.init(document.querySelector(".line6 .chart"));
    // 2.指定配置
    var option = {
        color: ['#1089E7', '#F57474', '#8B78F6', '#F8B448'],
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['起升钢丝绳1最大损伤量%', '起升钢丝绳2最大损伤量%', '\n', '起升钢丝绳3最大损伤量%', '起升钢丝绳4最大损伤量%'],
            textStyle: {
                color: "#4c9bfd"
            }

        },
        grid: {
            left: '1%',
            right: '3%',
            bottom: '3%',
            show: true,
            borderColor: '#012f4a',
            containLabel: true
        },
        toolbox: {
            left: '90%',
            top: '-2%',
            feature: {
                saveAsImage: {},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            data: ['3000', '4500', '6000', '8700', '10000', '12000', '14000', '17000', '20000'],
            axisTick: {
                lineStyle: {color: '#4c9bfd'},
                alignWithLabel: true
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                lineStyle: {color: '#4c9bfd'}
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                color: "#4c9bfd"
            },
            splitLine: {
                lineStyle: {
                    color: "#012f4a"
                }
            }
        },
        series: [
            {
                name: '起升钢丝绳1最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.2, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳2最大损伤量%',
                type: 'line',
                smooth: true,
                data: [1, 1.5, 2, 2.5, 3, 4, 5, 5.5, 7]
            },
            {
                name: '起升钢丝绳3最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.7, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            },

            {
                name: '起升钢丝绳4最大损伤量%',
                type: 'line',
                smooth: true,
                data: [0.5, 1.5, 1.8, 2, 3, 4, 5, 5.5, 7]
            }
        ]
    };
    myChart.setOption(option);
})();*/
