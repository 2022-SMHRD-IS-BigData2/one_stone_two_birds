// ---------- CHARTS ----------

/* 콤보-성별/ 나이에 따른 회원가입자 */
var options = {
  series: [{
  name: '남성',
  type: 'column',
  data: [10, 25, 35, 30, 27, 19, 18, 14]
}, {
  name: '여성',
  type: 'column',
  data: [9, 30, 48, 41, 28, 18, 20, 10]
}, {
  name: '전체',
  type: 'line',
  data: [19, 55, 83, 71, 55, 37, 38, 24]
}],
  chart: {
  height: 350,
  type: 'line',
  stacked: false
},
dataLabels: {
  enabled: false
},
stroke: {
  width: [1, 1, 4]
},
title: {
  /* text: '성별과 나이대에 따른 회원 가입자수 ', */
  align: 'left',
  offsetX: 110
},
xaxis: {
  categories: ['10대', '20대', '30대', '40대', '50대', '60대', '70대', '80대이상'],
},
yaxis: [
  {
    axisTicks: {
      show: true,
    },
    axisBorder: {
      show: true,
      color: '#008FFB'
    },
    labels: {
      style: {
        colors: '#008FFB',
      }
    },
    title: {
      text: "남성 가입자수",
      style: {
        color: '#008FFB',
      }
    },
    tooltip: {
      enabled: true
    }
  },
  {
    seriesName: 'Income',
    opposite: true,
    axisTicks: {
      show: true,
    },
    axisBorder: {
      show: true,
      color: '#00E396'
    },
    labels: {
      style: {
        colors: '#00E396',
      }
    },
    title: {
      text: "여성 가입자수",
      style: {
        color: '#00E396',
      }
    },
  },
  {
    seriesName: 'Revenue',
    opposite: true,
    axisTicks: {
      show: true,
    },
    axisBorder: {
      show: true,
      color: '#FEB019'
    },
    labels: {
      style: {
        colors: '#FEB019',
      },
    },
    title: {
      text: "나이대별 총 가입자수",
      style: {
        color: '#FEB019',
      }
    }
  },
],
tooltip: {
  fixed: {
    enabled: true,
    position: 'topLeft', // topRight, topLeft, bottomRight, bottomLeft
    offsetY: 30,
    offsetX: 60
  },
},
legend: {
  horizontalAlign: 'left',
  offsetX: 40
}
};

var chart = new ApexCharts(document.querySelector("#combo"), options);
chart.render(); 

// AREA CHART - 누적방문자수

var areaChartOptions = {
  series: [{
    name: 'Purchase Orders',
    data: [31, 40, 28, 51, 42, 109, 100]
  }, {
    name: 'Sales Orders',
    data: [11, 32, 45, 32, 34, 52, 41]
  }],
  chart: {
    height: 350,
    type: 'area',
    toolbar: {
      show: false,
    },
  },
  colors: ["#4f35a1", "#246dec"],
  dataLabels: {
    enabled: false,
  },
  stroke: {
    curve: 'smooth'
  },
  labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
  markers: {
    size: 0
  },
  yaxis: [
    {
      title: {
        text: 'Purchase Orders',
      },
    },
    {
      opposite: true,
      title: {
        text: 'Sales Orders',
      },
    },
  ],
  tooltip: {
    shared: true,
    intersect: false,
  }
};

var areaChart = new ApexCharts(document.querySelector("#area-chart"), areaChartOptions);
areaChart.render();



/* 거주지에 따른 회원수*/
var options = {
  series: [
  {
    data: [
      {
        x: '서울',
        y: 218
      },
   
      {
        x: '부산',
        y: 184
      },
      {
        x: '대구',
        y: 55
      },
      {
        x: '인천',
        y: 149
      },
      {
        x: '광주',
        y: 84
      },
      {
        x: '대전',
        y: 31
      },
      {
        x: '울산',
        y: 70
      },
      {
        x: '강원',
        y: 30
      },
      {
        x: '경기',
        y: 147
      },
      {
        x: '경남',
        y: 44
      },
      {
        x: '경북',
        y: 68
      },
      {
        x: '전남',
        y: 28
      },
      {
        x: '전북',
        y: 19
      },
      {
        x: '제주',
        y: 11
      },
      {
        x: '충남',
        y: 21
      },
      {
        x: '충북',
        y: 29
      }
    ]
  }
],
  legend: {
  show: false
},
chart: {
  height: 350,
  type: 'treemap'
},
title: {
  text: '',
  align: 'center'
},
colors: [
  '#3B93A5',
  '#F7B844',
  '#ADD8C7',
  '#EC3C65',
  '#CDD7B6',
  '#C1F666',
  '#D43F97',
  '#1E5D8C',
  '#421243',
  '#7F94B0',
  '#EF6537',
  '#C0ADDB'
],
plotOptions: {
  treemap: {
    distributed: true,
    enableShades: false
  }
}
};

var chart = new ApexCharts(document.querySelector("#live-chart"), options);
chart.render();