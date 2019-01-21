<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!<br>
<#--这个是一个注释-->
遍历list集合<br>
    <#if stus??>  <#--//判断变量是否为空 -->
        <#list stus as stu >
    <table>
        <tr>
            <td>${stu_index}</td>
            <td <#if stu.name='小明'>style="color: blue" </#if> >${stu.name!""}</td>   <#--判断#if-->
            <td>${stu.age}</td>
            <td>${stu.money}</td>
        </tr>
    </table>
        </#list>
    </#if>
获取map中的值
${stuMap['stu1'].name}<br>
${stuMap['stu1'].age}<br>
${stuMap['stu1'].money} <br>
<hr color="red">
${stuMap.stu2.name}<br>
${stuMap.stu2.age}<br>
${stuMap.stu2.money}<br>
遍历map
    <#list  stuMap?keys as stu>
        ${stu}<br>
    <table>
        <tr>
            <td>${stu_index}</td>
            <td>${stuMap[stu].name}</td>
            <td>${stuMap[stu].age}</td>
            <td>${stuMap[stu].money}</td>
        </tr>
    </table>
    </#list>
<hr color="blue">
    <#list stus as stu>
        ${stu.name!""}<br>   <#--如果为空则转化为空字符串-->
        ${stus?size}<br>
    </#list>

<#--转化为对象-->
    <#assign  student="{'name':'小仲子','age':'23'}"/>
    <#assign data=student?eval/>
    ${data.name}<br>
    ${data.age}


</body>
</html>
