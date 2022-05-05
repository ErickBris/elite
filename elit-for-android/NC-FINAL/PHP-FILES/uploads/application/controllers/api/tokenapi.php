<?php

require_once(APPPATH.'/libraries/REST_Controller.php');

class Tokenapi extends REST_Controller
{

          function token_post(){
	          
	          
	          $data = $this->post();
	          
	          if ($data == null) {
			  $this->response(array('error' => array('tokendata' => 'invalid_json')));
		      }
		                         
		    // if (!array_key_exists('tokendata', $data)) {
			//  $this->response(array('error' => array('tokendata' => 'require_email')));
		   //   }
		      
		     $token_data = array(
		     'tokenid' => $data['tokendata']
			    
		     );
		      
		    //  $this->dtoken->save($token_data);
		    
		    if ($this->dtoken->exists($token_data)) {
			$this->response(array('error' => array('message' => 'token_id_exist')));
		    } else {
			$this->dtoken->save($token_data);
			$this->response(array('token_id'=>$token_data['id']));
		    }
		                         
		                         
         }







}

?>