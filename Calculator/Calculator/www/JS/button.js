$(".Namber").on("click", (event) => {
    if ($("#first").get(0).value == "" && $("#second").get(0).value == "")
        $("#field").get(0).value = ""
    $("#field").get(0).value +=event.target.innerText
    if($("#sign").get(0).value == "")
        $("#first").get(0).value+=event.target.innerText;
    else
        $("#second").get(0).value+=event.target.innerText;
})

$("#CE").on("click", () =>{
    if ($("#field").get(0).value == "")
        $("#sign").get(0).value = "";
    $("#field").get(0).value = ""
    if($("#sign").get(0).value == "")
        $("#first").get(0).value = "";
    else
        $("#second").get(0).value = "";
})

$(".signs").on("click", (event) => {
    if ($("#sign").get(0).value == "" && ($("#field").get(0).value != $("#first").get(0).value))
        $("#first").get(0).value = $("#field").get(0).value
    if ($("#field").get(0).value == "")
        $("#sign").get(0).value = ""
    if($("#sign").get(0).value == ""){
        $("#sign").get(0).value = event.target.innerText
        $("#field").get(0).value= "";
    }
    else{
        $("#next").get(0).value = event.target.innerText
        $("#calculate").trigger("click")
    }

})