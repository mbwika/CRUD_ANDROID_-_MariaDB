<?php

$conn = mysqli_connect("localhost","root", "", "test");

$pid=$_GET['PID'];
$sql_query = "select ProductID, PName, Price from product where ProductID='$pid'";

$records = mysqli_query($conn,$sql_query);

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>