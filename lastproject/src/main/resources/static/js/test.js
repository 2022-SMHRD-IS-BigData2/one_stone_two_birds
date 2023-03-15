console.log(drugData)
let today = new Date();
let thirtyDaysAgo = new Date(today);
thirtyDaysAgo.setDate(today.getDate() - 30);

let dateString = "2023-03-15";
let targetDate = new Date(dateString);
let timeDiff = targetDate.getTime() - thirtyDaysAgo.getTime();
console.log(today)
console.log(thirtyDaysAgo);
console.log(timeDiff)

const day = timeDiff / (1000 * 60 * 60 * 24);
const hour = timeDiff / (1000 * 60 * 60);
const minute = timeDiff / (1000 * 60);
const second = timeDiff / 1000;
console.log("day: ", day);
console.log("hour: ", hour);
console.log("minute: ", minute);
console.log("second: ", second);