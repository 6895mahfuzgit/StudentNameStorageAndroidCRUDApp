<?php
$connect=  mysqli_connect('localhost','root','','task1');
if(mysqli_connect_errno())
{ die("cannot connect to database field:". mysqli_connect_error());   }


$query="SELECT * FROM  logins";
$result=  mysqli_query($connect, $query);

if(!$result){
  $info="ERROR IN QUERY";
}

while ($row=mysqli_fetch_assoc($result)) {

  $output[] =$row;
}


print(json_encode($output));

mysqli_close($connect)

// if(! $result)
// { die("Error in query");}
//  //get data from database
// //$output=array();
// while($row=  mysqli_fetch_assoc($result))
// {
//  $output[]=$row;  //$row['id']
// }
// print(json_encode($output));// this will print the output in json
// // 4 clear
// mysqli_free_result($result);
// //5- close connection
// mysqli_close($connect);
?>
