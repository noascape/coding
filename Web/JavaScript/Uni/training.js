// you can execute the code in the console in google, in the node.exe command block, in vsc powershell by typing "node training.js" in the right directory in this case. Change the directory with "cd JavaScript" here
// you can add the javascript in head or body of the html file with <script src=""></script>..
// You can use "" or ''
console.log('Hello World');


//Variables  [you can´t change const variables, use let for those]; variables are case sensitive; you can change the type of a variable 
let firstName = 'Mosh';                             // Sting Literal
let interestRate = 0.3;                             // Number Literal
let isApproved = false;                             // Boolean Literal   
let nothing;                                        // unefined   
let income = undefined;                             // undefined is not that common
let selectedColor = null;                           //clear value of a variable
 
interestRate = 1;
console.log(firstName);                             //typeof name --> 'string'
firstName = 3;

console.log(interestRate);
console.log(firstName);                             //typeof name --> 'number'


//Object 
let person = {
    name: 'Ruben',
    age: 30
};
person.name = 'John';                                   //dot notaion
person['name'] = 'Mary';                                //bracket notation   if user changes name during runtime f.e. with another variable
console.log(person)
console.log(person.name);


//Array
let selecetedColors = ['red', 'blue'];   
selecetedColors[2] = 1;                                 //you can store different types in an array
console.log(selecetedColors[2]);                        //Array starts with the index 0  
console.log(selecetedColors.length);                    //you can use many different functions with arrays


//Functions
function greet(name, lastName) {
    console.log('Function: Hello', name, lastName);       // just like in Python you can swith , with a +
}                                                         //functions don't need ; in the end
greet('John', 'Millner');
greet(person.name, 'Smith');

function square(number) {
    return number * number; 
}
console.log(square(2));


//If Condition 
if (true) {
    let a = 'block';
}

function isHuman(human) {
    if (human.dna == 'AACTG') {
        return true;
    } else {
        reject('failure');
    }
}


//Promise
function promise() {    
//   .then(success => {
//        console.log('yay!', success)
//    })
//    .catch(err => {
//        console.log('oh no!', err)
//   }) 
}

//Non blocking event loop
setTimeout(() => {                                  
    console.log('5 seconds in the future')
}, 5000);


//Use code from a file elsewhere      [--> in different file: "import helper from './help.js'"]
export default function helper() {
    console.log('help me, help you');
}

//dynamic import 
// const lazyBundle = await import('./lazy.js');


//Event listener
const btn = document.querySelector('.button');
btn.addEventListener('click', () => {
    document.body.style.backgroundColor = 'red';
});