<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
ul, #myUL {
  list-style-type: none;
}

#myUL {
  margin: 0;
  padding: 0;
}
.node{
background-color: white;
}
.node-active{
background-color: blue;
}
.caret {
  cursor: pointer;
  -webkit-user-select: none; /* Safari 3.1+ */
  -moz-user-select: none; /* Firefox 2+ */
  -ms-user-select: none; /* IE 10+ */
  user-select: none;
}

.caret::before {
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}

.caret-down::before {
  -ms-transform: rotate(90deg); /* IE 9 */
  -webkit-transform: rotate(90deg); /* Safari */'
  transform: rotate(90deg);  
}

.nested {
  display: none;
}

.active {
  display: block;
}
</style>
</head>
<body>

<h2>Выбор офиса</h2>
<p>A tree view represents a hierarchical view of information, where each item can have a number of subitems.</p>
<p>Click on the arrow(s) to open or close the tree branches.</p>

<ul id="myUL">
<li><span class="caret">office1</span><ul class="nested"><li>sub1</li><li><span class="caret">sub2</span><ul class="nested"><li><span class="caret">sub10</span><ul class="nested"><li>sub13</li></ul><li>sub11</li></ul><li>sub3</li></ul><li>office2</li><li><span class="caret">office3</span><ul class="nested"><li>sub4</li><li><span class="caret">sub5</span><ul class="nested"><li>sub12</li></ul><li>sub6</li></ul><li><span class="caret">office4</span><ul class="nested"><li>sub7</li><li>sub8</li><li>sub9</li></ul><li>office5</li><li>office6</li><li>office7</li>






  <li><span class="caret">Beverages</span>
    <ul class="nested">
      <li class="node">Water</li>
      <li class="node">Coffee</li>
      <li class="node"><span class="caret">Tea</span>
        <ul class="nested">
          <li class="node">Black Tea</li>
          <li class="node">White Tea</li>
          <li class="node"><span class="caret">Green Tea</span>
            <ul class="nested">
              <li class="node">Sencha</li>
              <li class="node">Gyokuro</li>
              <li class="node">Matcha</li>
              <li class="node">Pi Lo Chun</li>
            </ul>
          </li>
        </ul>
      </li>  
    </ul>
  </li>
</ul>

<script>
var toggler = document.getElementsByClassName("caret");
var i;
var j;
for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
    this.parentElement.querySelector(".nested").classList.toggle("active");
    this.classList.toggle("caret-down");
  });
}
var toggler = document.getElementsByClassName("node");

for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
 // toggler[j].style.backgroundColor = "white";
    this.classList.toggle("node-active");
    j=this;
  });
}
</script>

</body>
</html>
