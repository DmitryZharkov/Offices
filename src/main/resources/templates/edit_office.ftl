<#if office?? && noErrors??>
        Офис добавлен<br>
        Наименование: ${office.name}<br>
        Адрес: ${office.addr}<br>
        <#else>
        <form action="/edit_office" id="office_form" method="post">
            <p style="color: RED">${error}</p>
            <br>
            Наименование:<br>
            <input id="edit_office_name" type="text" name="name" value=${office.name}>
            <br><br>
            Адрес:<br>
            <input id="edit_addr" type="text" name="addr" value=${office.addr}>
            <br><br>
            <input type="submit" value="Сохранить">
        </form>
        </#if>
