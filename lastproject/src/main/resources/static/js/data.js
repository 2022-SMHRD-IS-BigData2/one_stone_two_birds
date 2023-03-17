let maleData = []
let femaleData = []
let allData = []

for (var i = 10; i <= 80; i += 10) {
	let female = ageGenderCountData.female[i]
	let male = ageGenderCountData.male[i]
	let all = female + male

	femaleData.push(female)
	maleData.push(male)
	allData.push(all)
}

let loginData = [];
let loginKeys = [];

let keys = Object.keys(loginCountData);
keys.sort(function(a, b) {
	let dateA = new Date(a);
	let dateB = new Date(b);
	return dateA - dateB; // 순서 반대로 변경
});

let maxData = Math.max(...maleData, ...femaleData, ...allData);

let rangeData = maxData + 4;

for (let i = 0; i < keys.length; i++) {
	let key = keys[i];
	let value = loginCountData[key];
	loginKeys.push(key);
	loginData.push(value);
}

let areaData = Object.entries(areaCountData).map(([area, count]) => ({ x: area, y: count }));
areaData.sort((a, b) => b.y - a.y);
let livingData = JSON.stringify(areaData);
