function time()
{
    document.getElementById("time").innerHTML=dateToString(new Date());
    setTimeout(time,1000);
}

function dateToString(date)
{
    return necessaryAdd(date.getHours())+":"+
        necessaryAdd(date.getMinutes())+":"+necessaryAdd(date.getSeconds());
}

function necessaryAdd(number)
{
    return number<10 ? "0"+number : number;
}