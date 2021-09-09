<!DOCTYPE HTML >

<html lang = "hu">

	<head>
	 <title>Feldolgozó php</title>
	 <meta charset="UTF-8">
	 </head>
		
		<body>
		<body style="background-color:powderblue;">
		<font style="font-size:16pt">
		
		<h2><center>HTML5 űrlap</center></h2>
		
		<?php
		
			
			echo ("<strong>Név:</strong> " .$_POST['nev']. "<br><br>");
			
			echo ("<strong>PIN kód:</strong> " .$_POST['kod']. "<br><br>");
			
			echo ("<strong>Gyümölcs:</strong> " .$_POST['gyumolcs']. "<br><br>");
			
			echo ("<strong>Életkor:</strong> " .$_POST['eletkor']. "<br><br>");
			
			echo ("<strong>Lábméret:</strong> " .$_POST['labmeret']. "<br><br>");
			
			echo ("<strong>Önbizalom:</strong> " .$_POST['onbizalom']."%"."<br><br>");
			
			
			
			?>
			<a href="urlap_szabopeter.html">vissza az űrlapra...</a>
			
			</font>
			
			</body>
			
		</html>