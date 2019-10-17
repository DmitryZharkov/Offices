<#if sub?? && noErrors??>
        Подразделение добавлено<br>
        Наименование: ${sub.name}<br>
        ФИО: ${sub.fio}<br>
        <#else>
        <form action="/edit_sub" method="post">
            <p style="color: RED">${error}</p>
            <br>
            Наименование:<br>
            <input id="edit_sub_name" type="text" name="name" value=${sub.name}>
            <br><br>
            ФИО:<br>
            <input id="edit_fio" type="text" name="fio" value=${sub.fio}>
            <br><br>
            <input type="submit" value="Сохранить">
        </form>
        </#if>
