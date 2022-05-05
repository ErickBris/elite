<?php 
require_once(APPPATH.'/libraries/REST_Controller.php');

class Imgdata extends REST_Controller
{
	function __construct()
	{
		parent::__construct();	
	}
	
	function index_get($id)
	{
	
	    header('Content-type: application/json');
		$imgsz  = $this->image->get_all_by_type($id->id, 'event')->result();
		
		$imgdata = array();
		$imgdataz = array();
		foreach ($imgsz as $img){
			
			$imgdata[] = $img;
			
		}
		$imgdataz =array('images'=>$imgdata);
		$this->response ($imgdataz);

	}
	
	function get_feed_images(&$news)
	{
			
	    $result = $this->image->get_all_by_type($news->id, 'event')->result();
	    
	    	    
	   $news->images = $result;
	  
	  	  //  $news  = array('images'=>$result);
		
		
	}
	
	function imageevent ($id){
	
		
		$imgsz  = $this->image->get_all_by_type($news->id, 'event')->result();
		
		$imgdata = array();
		$imgdataz = array();
		foreach ($imgsz as $img){
			
			$imgdata[] = $img;
			
		}
		$imgdataz =array('images'=>$imgdata);
		$this->response ($imgdataz);
		
		
		
		
		
	}
	
	
}
?>