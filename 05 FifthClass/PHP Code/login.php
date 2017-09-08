<?php 

    $response = array('status' => false, 'message' => 'REST API method missmatch');

    if ($_SERVER['REQUEST_METHOD'] === 'POST')
    {        
        $data = file_get_contents('php://input');
        $json_data = json_decode($data , true);
    
        //code to process data
        if ($data == "" || empty($json_data['user_name']) || empty($json_data['password'])){
            $response = array('status' => false, 'message' => 'Invalid Values');
        }
        else if($json_data['user_name']=='user' && $json_data['password']=='123'){
            $response = array('success' => true, 'message' => 'Login successful!');
        } 
        else
            $response = array('status' => false, 'message' => 'User name or password is wrong!');
            
    }

    echo json_encode($response);

?>
