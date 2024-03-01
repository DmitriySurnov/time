<?php
include_once ('model/functions.php');
session_start();
if (count($_POST) > 0)
    calculate($_POST['first'],$_POST['second'],$_POST['sign'])

?>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="Styles/main.css">
    <title>Calculator</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script type="module" src="JS/button.js"></script>
</head>
<body>
<H1>Calculator</H1>
<div id="Calculator">
    <div>
        <?php
        $value = "";
        if (isset($_SESSION['error']) and count($_POST) > 0){
            if ($_SESSION['error'] == 0){
                if ($_POST['next'] == "=")
                    $value = $_SESSION['result'];
            }else{
                $value = errorText();
            }
        }
        ?>
        <input type="text" readonly id="field" value="<?=$value?>">
    </div>
    <div>
        <?php
        $buttons = array("1","2","3");
        foreach ($buttons as $Val)
            echo ("<button class='buttons Namber'>$Val</button>");
        echo ("<button class='buttons signs'>+</button>")
        ?>
    </div>
    <div>
        <?php
        $buttons = array("4","5","6");
        foreach ($buttons as $Val)
            echo ("<button class='buttons Namber'>$Val</button>");
        echo ("<button class='buttons signs'>-</button>");
        ?>
    </div>
    <div>
        <?php
        $buttons = array("7","8","9");
        foreach ($buttons as $Val)
            echo ("<button class='buttons Namber'>$Val</button>");
        echo ("<button class='buttons signs'>*</button>")
        ?>
    </div>
    <div>
        <?php
        echo ("<button id='CE' class='buttons'>CE</button>");
        echo ("<button class='buttons Namber'>0</button>");

        $buttons = array("=","/");
        foreach ($buttons as $Val)
            echo ("<button class='buttons signs'>$Val</button>")
        ?>
    </div>
    <div id="info">
        <form action="" method="post">
            <?php
            $fields = array("first","second","sign","next");
            $isInfo =false;
            if (count($_POST) > 0)
                if ($_POST['next'] != "="){
                    $first = $_SESSION['result'];
                    $sign = $_POST['next'];
                    $isInfo = true;
                }
            foreach ($fields as $Val){
                if ( $isInfo && $Val == "first")
                    echo ("<input type='text' name=$Val id=$Val value=$first >" );
                elseif ($isInfo &&  $Val == "sign")
                    echo ("<input type='text' name=$Val id=$Val value=$sign >" );
                else
                    echo ("<input type='text' name=$Val id=$Val>");
            }
            ?>
            <input type="submit" id="calculate" value="" />
        </form>
    </div>
</div>

</body>
<footer>
    <div>
        <span> Crated by Surnov Dmitriy (2023) </span>
    </div>
</footer>
</html>
