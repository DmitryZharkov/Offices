<#if noErrors??>
        Подразделение добавлено<br>
        Наименование: ${employee.name}<br>
        ФИО: ${employee.birth?datetime}<br>
        <#else>
<form method="post" enctype="No Method Used" action="addemployee">
<p style="color: RED">${error}</p>
            <br>
ФИО:<br>
<input type="text" name="name" value="${employee.name}">
<br><br>
Дата рождения(дд/мм/гггг):<br>
<input id="birth" type="text" name="birth" value="${employee.birth?date?string.short?replace(".","/")}">
<br><br>
Телефон:<br>
<input type="number" name="tel" value="${employee.tel}">
<br><br>
Почта:<br>
<input type="text" name="mail" value="${employee.mail}">
<br><br>
<input type="submit" name="submit" value="Добавить">
<input type="reset">
</#if>
<script>
$(document).ready(function(){
    var birth1=${employee.birth?date?string.short};
    var str1 = birth1.replace(/./g,"/");
    $('#birth').val(str1);
});
</script>