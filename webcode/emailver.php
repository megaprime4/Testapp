<?php
	require_once "connect.php";
	
	$email = $_GET["email"];
	$code = $_GET["code"];
	$dbcode = "";
	
	$query = "SELECT * FROM USERS WHERE email = '$email'";
	
	$result = mysqli_query($con, $query);
	
	if($result) {
		while($row = mysqli_fetch_assoc($result)) {
			$dbcode = $row["code"];
		}
		
		if($dbcode == $code) {
			$qry = "UPDATE USERS SET confirmed='1', code='0' WHERE email='$email'";
			$fresult = mysqli_query($con, $qry);
			echo "Congratulation! Your account has been activated!";
		} else {
			echo "E-mail verification failed!";
		}
	} else {
		echo "No results was returned!";
	}
?>