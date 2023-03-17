// ---------- CHARTS ----------


/* 콤보-성별/ 나이에 따른 회원가입자 */
var options = {
	series: [{
		name: '남성',
		type: 'column',
		data: maleData
	}, {
		name: '여성',
		type: 'column',
		data: femaleData
	}, {
		name: '전체',
		type: 'line',
		data: allData
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
			min: 0,
			max: rangeData,
			tickAmount: rangeData,
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
			min: 0,
			max: rangeData,
			tickAmount: rangeData,
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
			min: 0,
			max: rangeData,
			tickAmount: rangeData,
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
		data: loginData // 일일 방문자수 데이터
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
	labels: loginKeys, // 최근 날짜부터 1주일 전까지
	markers: {
		size: 0
	},
	yaxis: [
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
			data: areaData
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