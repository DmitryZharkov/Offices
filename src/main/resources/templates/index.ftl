<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="generator" content="CoffeeCup HTML Editor (www.coffeecup.com)">
    <meta name="dcterms.created" content="Вт, 25 сен 2018 11:34:14 GMT">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <title></title>
    
    <style type="text/css">
	/* Style the buttons */
.tbl {
  border: none;
  outline: none;
  padding: 10px 16px;
  background-color: #f1f1f1;
  cursor: pointer;
}

/* Style the active class (and buttons on mouse-over) */
.tactive, .tbl:hover {
  background-color: #666;
  color: white;
}
    <!--
	f1{
	padding: 0px 0px 750px 0px;
	 }
	 f2{
	padding: 300px 100px 400px 0px;
	 }
    body {
      color:#000000;
      background-color:#FFFFFF;
      background-image:url('Background Image');
      background-repeat:no-repeat;
    }
    a  { color:#0000FF; }
    a:visited { color:#800080; }
    a:hover { color:#008000; }
    a:active { color:#FF0000; }
    -->
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

    <!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>
  <body>
	<!-- Start of FORM -->
<!-- Frames are to be placed in the top of an -->
<!--      HTML page BEFORE the BODY tag       -->
<div id=f1 height="608" width="300">
<h2>Выбор офиса</h2>
${s1}
<ul id="myUL">
</div>
<div style="position: absolute;top: 8px;left: 316px;background-color:white;color:black;" id=f2 height="200" width="300">
<#if office?? >
        Офис добавлен<br>
        Наименование: ${office.name}<br>
        Адрес: ${office.addr}<br>
        <#else>
        <form action="/add_office" id="office_form" method="post">
            Наименование:<br>
            <input id="edit_office_name" type="text" name="name">
            <br><br>
            Адрес:<br>
            <input id="edit_addr" type="text" name="addr">
            <br><br>
            <input type="submit" value="Добавить офис">
        </form>
        </#if>
<!-- End of FORM -->
 </div>
<div hidden style="position: absolute;top: 8px;left: 616px;background-color:white;color:black;" id=f3 height="200" width="300">
<#if sub?? >
        Подразделение добавлено<br>
        Наименование: ${sub.name}<br>
        ФИО: ${sub.fio}<br>
        <#else>
        <form action="/add_sub" method="post">
            Наименование:<br>
            <input id="edit_sub_name" type="text" name="name">
            <br><br>
            ФИО:<br>
            <input id="edit_fio" type="text" name="fio">
            <br><br>
            <input type="submit" value="Добавить подразделение">
        </form>
        </#if>
<!-- End of FORM -->
 </div>
<div hidden id="f4" style="position: absolute;top: 8px;left: 916px;background-color:white;color:black;" height="200" width="300">
<p hidden id="office_name" class="office_name">Офис: </p>
<p hidden id="office_addr" class="office_addr">Адрес:</p>
<p hidden id="sub_name" class="sub_name"> Подразделение: </p>
<p hidden id="sub_fio" class="sub_fio"> ФИО: </p>
<button id="delete_off_button" type="button" onclick="DeleteOffice()">Удалить</button> 
<button id="edit_off_button" type="button" onclick="EditOffice()">Изменить</button> 
<!-- End of FORM -->
 </div>
<div hidden style="position: absolute;top: 8px;left: 1216px;background-color:white;color:black;" class="eo" height="200" width="300">
<#if office?? >
        Офис добавлен<br>
        Наименование: ${office.name}<br>
        Адрес: ${office.addr}<br>
        <#else>
        <form action="/edit_office" id="office_form" method="post">
            Наименование:<br>
            <input id="edit_office_name2" type="text" name="name">
            <br><br>
            Адрес:<br>
            <input id="edit_addr2" type="text" name="addr">
            <br><br>
            <input type="submit" on value="Сохранить" onclick=Hide_edito()>
        </form>
        </#if>
<!-- End of FORM -->
 </div>
<div hidden style="position: absolute;top: 8px;left: 1216px;background-color:white;color:black;" class="es" height="200" width="300">
<#if sub?? >
        Подразделение добавлено<br>
        Наименование: ${sub.name}<br>
        ФИО: ${sub.fio}<br>
        <#else>
        <form action="/edit_sub" method="post">
            Наименование:<br>
            <input id="edit_sub_name2" type="text" name="name">
            <br><br>
            ФИО:<br>
            <input id="edit_fio2" type="text" name="fio">
            <br><br>
            <input type="submit" value="Сохранить" onclick=Hide_edits()>
        </form>
        </#if>
<!-- End of FORM -->
</div>
<div style="position: absolute;top: 216px;left: 316px;" id=f2 height="400" width="1000"></div>
<div id="ta" style="position: absolute;top: 216px;left: 316px; text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;">
<p> Сотрудники: </p>
<table id="cap" width="100%" border="1" cellpadding="2" cellspacing="2" style="background-color: #ffffff;">
<tr valign="top">
<td width="271" style="border-width : 0px;">ФИО<br />
</td>
<td width="89" style="border-width : 0px;">Дата рождения<br />
</td>
<td width="118" style="border-width : 0px;">Телефон<br />
</td>
<td width="131" style="border-width : 0px;">Почта<br />
</td>
</tr>
<table id="tb1" width="100%" border="1" cellpadding="2" cellspacing="2" style="background-color: #ffffff;">
<tr id="1" class="tbl tactive" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="2" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="3" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="4" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="5" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="6" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="7" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="8" class="tbl" valign="top">
<td width="271" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="150" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="9" class="tbl" valign="top">
<td width="171" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
<tr id="10" class="tbl" valign="top">
<td width="171" style="border-width : 0px;"><br />
</td>
<td width="89" style="border-width : 0px;"><br />
</td>
<td width="118" style="border-width : 0px;"><br />
</td>
<td width="131" style="border-width : 0px;"><br />
</td>
</tr>
</table><!-- Start of FORM -->
<div hidden id="controls">
<button hidden id="DeleteEmployee" type="button" onclick="DeleteEmployee()">Удалить</button> 
<button hidden id="EditEmployee" type="button" onclick="EditEmployee()">Изменить</button>
<button hidden id="PrevPage" type="button" onclick="PrevPage()"><<</button>
<select hidden id="SelectPage" onchange="PageChanged()">
</select>
<button hidden id="NextPage" type="button" onclick="NextPage()">>></button>
<span id="sorth"> Сортировать:<span> 
<select hidden id="SelectSort" onchange="SortChanged()">
<option value="1">ФИО
  <option value="2">Дата рождения
  <option value="3">Телефон
  <option value="4">Почта
</select>
</div>
<div hidden id="editemployee">
<@spring.bind "Employees"/>
<#if Employees?? && noErrors??>
        Подразделение добавлено<br>
        Наименование: ${Employees.name}<br>
        ФИО: ${Employees.birth}<br>
        <#else>
<form method="post" enctype="No Method Used" action="editemployee">
<br>ФИО:<br>
<input id="edit_employee_name" type="text" name="name" value="">
<@spring.showErrors "<br>"/>
<br><br>
Дата рождения(дд/мм/гггг):<br>
<input id="edit_employee_date" type="text" name="birth" value="">
<@spring.showErrors "<br>"/>
<br><br>
Телефон:<br>
<input id="edit_employee_tel" type="text" name="tel" value="">
<@spring.showErrors "<br>"/>
<br><br>
Почта:<br>
<input id="edit_employee_mail" type="text" name="mail" value="">
<@spring.showErrors "<br>"/>
<br><br>
<input type="submit" name="submit" value="Сохранить">
<input type="reset">
</#if>
</form>
</div>
<div hidden id="add_employee">
<#if employee?? >
        Подразделение добавлено<br>
        Наименование: ${employee.name}<br>
        ФИО: ${employee.date}<br>
        <#else>
<form method="post" enctype="No Method Used" action="addemployee">
<br>ФИО:<br>
<input type="text" name="name" value="">
<br><br>
Дата рождения(дд/мм/гггг):<br>
<input type="text" name="birth" value="">
<br><br>
Телефон:<br>
<input type="text" name="tel" value="">
<br><br>
Почта:<br>
<input type="text" name="mail" value="">
<br><br>
<input type="submit" name="submit" value="Добавить">
<input type="reset">
</#if>
</form>
<!-- <input type="button" onclick="showemployee()" value="Добавить сотрудника">>
<!-- End of FORM -->
</div>
<script>
// Add active class to the current button (highlight it)
var officesub=0;
var officename="";
var officeaddr="";
var addr="";
var header = document.getElementById("ta");
var btns = header.getElementsByClassName("tbl");
var employees=0;
var pagen=0;
var sel_employee;
var names = new Array(10);
var employeesL;
var office;
var sub;
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function() {
    var current = document.getElementsByClassName("tactive");
    current[0].className = current[0].className.replace(" tactive", "");
    this.className += " tactive";
    var a = parseInt(this.id);
    if(!(a>employees))
    {
    sel_employee=a;
    $('#DeleteEmployee').show();
    $('#EditEmployee').show();
    $('#editemployee').hide();
    }
    else{
    $('#DeleteEmployee').hide();
    $('#EditEmployee').hide();
    $('#editemployee').hide();
}
  });
}

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
var toggler = document.getElementsByClassName("sel");

for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
employees=0;
var table = document.getElementById("tb1");
for (var i = 0, row; row = table.rows[i]; i++) {
   for (var j = 0, col; col = row.cells[j]; j++) {
    col.innerHTML="";
   }  
}
var table = document.getElementById("tb1");
for (var i = 0, row; row = table.rows[i]; i++) {
   for (var j = 0, col; col = row.cells[j]; j++) {
    col.innerHTML="<br />";
   }  
}
$('#PrevPage').hide();
$('#SelectPage').hide();
$('#NextPage').hide();
$('#SelectSort').hide();
$('#sorth').hide();
$('#controls').hide();
$('.eo').hide();
$('.es').hide();
request();
   $.ajax({
type: "post",
        url: "http://localhost:8080/getoffice"+this.id
    }).then(function(data) {
$('.office_name').html("Офис:").hide();
$('.office_addr').html("Адрес:").hide();
$('.sub_name').html("Подразделение:").hide();
$('.sub_fio').html("Фио:").hide();
$('#f3').show();
$('#f4').show();
if(data.addr!=null){
officesub=1;
$('.office_name').append(data.name).show();
$('.office_addr').append(data.addr).show();
$('#add_employee').hide();
$('#editemployee').hide();
$('#controls').hide();
officename=data.name;
office=data;
addr=data.addr;
}
if (data.fio!=null){
officesub=2;
officename=data.name;
sub=data;
fio=data.fio;
$('.sub_name').append(data.name).show();
$('.sub_fio').append(data.fio).show();
$('#add_employee').show();
$.ajax({
type: "post",
        url: "http://localhost:8080/getemployees"
    }).then(function(list) {
var table = document.getElementById("tb1");
employeesL=list;
if (list.length!=0)
{
$('#SelectSort').show();
$('#sorth').show();
$('#controls').show();
}
for(var i in list)
{
    var birth=list[i].birth;
    var str1 = birth.replace(/-/g,"/");
    var str2 = str1.slice(0,str1.indexOf("T"));
     var row=table.rows[i];
     row.cells[0].innerHTML= list[i].name;
     row.cells[1].innerHTML= str2;
     row.cells[2].innerHTML= list[i].tel;
     row.cells[3].innerHTML= list[i].mail;
     names[i]=list[i].name;
     employees=employees+1;
}
if(!(list.length<10))
{
$.ajax({
type: "post",
        url: "http://localhost:8080/getpagen"
    }).then(function(data) {
pagen=data;
if (data>0)
{$('#SelectPage').html("")
for (i=0; i<data; i++)
{
$('#SelectPage').append("<option value=\""+i+"\">"+i+"</option>");
}
$('#PrevPage').show();
$('#SelectPage').show();
$('#NextPage').show();
}
});
}
});
}
    });
    j=this;
  });
}
function DeleteOffice(){
var office_sub;
switch (officesub){
case 1:
office_sub=office;
break;
case 2:
office_sub=sub;
break;
}
if(office_sub.hc==0)
{
if (confirm("Удалить?")) {
$.ajax({
type: "post",
        url: "http://localhost:8080/delete"
    }).then(function(data) {
location.reload();
});
}
}
else
{
alert("Офис имеет подразделения!");
}
}
function EditOffice(){
$.ajax({
type: "post",
        url: "http://localhost:8080/edit"
    }).then(function(data) {
});
switch (officesub){
case 1:
var textOne=document.getElementById("edit_office_name2");
textOne.value=officename;
var textOne=document.getElementById("edit_addr2");
textOne.value=addr;
$('.eo').show();
$('#f4').show();
break;
case 2:
var textOne=document.getElementById("edit_sub_name2");
textOne.value=officename;
var textOne=document.getElementById("edit_fio2");
textOne.value=fio;
$('.es').show();
break;
}
}
function Hide_edito(){
$('.eo').hide();
}
function Hide_edits(){
$('.es').hide();
}
function PageChanged()
{
var table = document.getElementById("tb1");
for (var i = 0, row; row = table.rows[i]; i++) {
   for (var j = 0, col; col = row.cells[j]; j++) {
    col.innerHTML="<br />";
   }  
}
employees=0;
$.ajax({
type: "post",
        url: "http://localhost:8080/gete_page"+$('#SelectPage').val()
    }).then(function(list) {
var table = document.getElementById("tb1");
employeesL=list;
for(var i in list)
{
    var birth=list[i].birth;
    var str1 = birth.replace(/-/g,"/");
    var str2 = str1.slice(0,str1.indexOf("T"));
     var row=table.rows[i];
     row.cells[0].innerHTML= list[i].name;
     row.cells[1].innerHTML= str2;
     row.cells[2].innerHTML= list[i].tel;
     row.cells[3].innerHTML= list[i].mail;
     employees=employees+1;
     names[i]=list[i].name;
}
});

}
function PrevPage()
{
if($('#SelectPage').val()!=0)
{
$('#SelectPage').val($('#SelectPage').val()-1)
PageChanged();
}
}
function NextPage()
{
temp=parseInt($('#SelectPage').val());
if($('#SelectPage').val()<(pagen-1))
{
temp=temp+1;
$('#SelectPage').val(temp)
PageChanged();
}
}
function SortChanged()
{
$('#SelectPage').val(0)
$.ajax({
type: "post",
        url: "http://localhost:8080/setsort"+$('#SelectSort').val()
    }).then(function(data) {
});employees=0;
$.ajax({
type: "post",
        url: "http://localhost:8080/getemployees"
    }).then(function(list) {
var table = document.getElementById("tb1");
employeesL=list;
for(var i in list)
{
    var birth1=list[i].birth;
    var str1 = birth1.replace(/-/g,"/");
    var str2 = str1.slice(0,str1.indexOf("T"));
     var name = list[i].tel;
     var row=table.rows[i];
     row.cells[0].innerHTML= list[i].name;
     row.cells[1].innerHTML= str2;
     row.cells[2].innerHTML= list[i].tel;
     row.cells[3].innerHTML= list[i].mail;
     employees=employees+1;
     names[i]=list[i].name;
}
});
}
function showemployee(){
}
function DeleteEmployee()
{
if (confirm("Удалить?")) {
$.ajax({
type: "post",
        url: "http://localhost:8080/delete_employee"+names[sel_employee-1]
    }).then(function(data) {
location.reload();
});
}
}
function EditEmployee()
{
$('#editemployee').show();
var birth=employeesL[sel_employee-1].birth;
var str1 = birth.replace(/-/g,"/");
var str2 = str1.slice(0,str1.indexOf("T"));
var textOne=document.getElementById("edit_employee_name");
textOne.value=employeesL[sel_employee-1].name;
var textOne=document.getElementById("edit_employee_date");
textOne.value=str2;
var textOne=document.getElementById("edit_employee_tel");
textOne.value=employeesL[sel_employee-1].tel;
var textOne=document.getElementById("edit_employee_mail");
textOne.value=employeesL[sel_employee-1].mail;
}
function request(){
   $.ajax({
type: "post",
        url: "http://localhost:8080/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
}
</script>
  </body>
</html>