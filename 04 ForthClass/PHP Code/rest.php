<?php 

    $response = array('status' => false, 'message' => 'Invalid Values');

    if ($_SERVER['REQUEST_METHOD'] === 'POST'){
        
        $data = file_get_contents('php://input');
        $json_data = json_decode($data , true);
    
        //code to process data
        if ($data == "" || empty($json_data['sender_name']) || empty($json_data['age'])){
            $response = array('status' => false, 'message' => 'Invalid Values');
        }
        else{
            $response = array('success' => true, 'message' => 'Hello '.$json_data['sender_name'].'! You are '.$json_data['age'].' years old.');
        }
     
    }

    echo json_encode($response);
?>