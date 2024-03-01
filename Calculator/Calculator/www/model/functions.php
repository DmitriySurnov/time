<?php

function calculate($first,$second,$sign){
    if (strlen($sign) == 1 and settype($first, "int")
        and settype($second, "int")){
        switch ($sign){
            case "+":{
                $_SESSION['result'] = $first+$second;
                break;
            }
            case "-":{
                $_SESSION['result'] = $first-$second;
                break;
            }
            case "*":{
                $_SESSION['result'] = $first*$second;
                break;
            }
            case "/":{
                if ($second == 0){
                    $_SESSION['error'] = 3;
                    return;
                }
                $_SESSION['result'] = $first/$second;
                break;
            }
            default:{
                $_SESSION['error'] = 2;
                return;
            }
        }
        $_SESSION['error'] = 0;
    }
    else{
        $_SESSION['error'] = 1;
    }
}

function errorText(){
    $_SESSION['result'] = "";
    switch ($_SESSION['error'] ){
        case 1: return "Something went wrong.";
        case 2: return "Unknown action";
        case 3: return "You can't divide by zero";
        default: return "";
    }
}
