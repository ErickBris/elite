<?php 
require_once(APPPATH.'/libraries/REST_Controller.php');

class Photos extends REST_Controller
{
	function __construct()
	{
		parent::__construct();	
	}
	
	function index_get()
	{
		$feeds = $this->photo->get_all()->result();
		
		$data = array();
		$dataz = array();
		foreach ($feeds as $news) {
			$this->get_feed_images($news);
			$data[] = $news;
		}
		$dataz =array('photo'=>$data);
		
		$this->response($dataz);
	}
	
	function get_feed_images(&$news)
	{
		//$news->added = $this->ago($news->added);
		$news->images = $this->image->get_all_by_type($news->id, 'photo')->result();
	}
}
?>