<?php
class Uploadermix extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
		$this->load->helper('file');
		
		$this->load->library('upload');
					
		$config['upload_path'] = './uploadsmix';
		$config['allowed_types'] = 'mp3';
		$config['overwrite'] = TRUE;
		$config['encrypt_name'] = TRUE;
		
		$this->upload->initialize($config);
		$this->load->library('image_lib');
	}
	
	function upload($files,$userId=0,$type="")
	{
		$data = array();
		
		foreach ($files as $field=>$file) {
      	if ($userId==0) {	
      		$_FILES[$field]['name'] = $_FILES[$field]['name'];
      	} else {
      		//$temp = explode(".", $_FILES[$field]["name"]);
      		//$extension = end($temp);
      		$extension = '.mp3';
      		$_FILES[$field]['name'] = $userId . "-" . $type . $extension;
         	
      		
      		if (file_exists(".uploadsmix/".$_FILES[$field]['name'])) { 
      		   unlink(".uploadsmix/".$_FILES[$field]['name']);
      		}
      	}
      	
      	
      	if ($file['error'] == 0) {	
        		
        		
        		if ($this->upload->do_upload($field)) {
            	$data[] = $this->upload->data();
           
        		} else {
            		$data['error'] = $this->upload->display_errors();
        		}
      	}
      }
      
      return $data;
	}
}
?>