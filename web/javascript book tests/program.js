var add = function(a,b) {
	return a+b;
}

var array = [3,4];

var sum = add.apply(null, array);


document.writeln(sum);