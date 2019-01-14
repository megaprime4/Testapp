<?php
	require_once "connect.php";
	
	if(!$con) {
		echo "Connection failed!";
	} else {
		if($_SERVER['HTTP_USER_AGENT'] == "Testapp") {
			if($_SERVER["REQUEST_METHOD"] == "POST") {
				if(isset($_POST['name']) && isset($_POST['id']) && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['bvc_reg']) && isset($_POST['password'])) {
					$name = $_POST['name'];
					$id = $_POST['id'];
					$email = $_POST['email'];
					$phone = $_POST['phone'];
					$bvc_reg = $_POST['bvc_reg'];
					$password = $_POST['password'];
					
					$dupe = "SELECT * FROM USERS WHERE email = '$email'";
					$dupe_results = mysqli_query($con, $dupe);
					if(mysqli_num_rows($dupe_results)>0) {
						echo "Email already exists!";
					} else {
						$code = rand();
						
						$sql = "INSERT INTO USERS (name, id, email, phone, bvc_reg, password, confirmed, code) VALUES ('$name', '$id', '$email', '$phone', '$bvc_reg', '$password', '0', '$code')";
						
						if(mysqli_query($con, $sql)) {
							$header = "From: DoNotReply@testapp.com";
							$to = $email;
							$subj = "Email verification";
							$msg = "
								$name,
								
								Please click on the link below
								
								http://vetdevelopers.com/connect/emailver.php?email=$email&code=$code
							";
							mail($to,$subj,$msg,$header);
							echo "Please check your e-mail!";
						} else {
							echo "Error: " . $sql . "<br>" . mysqli_error($con);
						}
					}
					
					mysqli_close($con);
					
				} else {
					echo "Missing required field!";
				}
			} else {
				echo "Improper request method!";
			}
		} else {
			echo "Invalid platform!";
		}
	}
?>