<?php
require_once('access.php');
class Newsies extends Access
{
	function __construct()
	{
		parent::__construct('newsies');
		$this->load->library('uploader');
	}
	
	//create
	function add()
	{
		$this->check_access('add');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {			
			$upload_data = $this->uploader->upload($_FILES);
			
			if (!isset($upload_data['error'])) {
				$feed_data = $this->input->post();
				//$img_desc = $feed_data['image_desc'];
				unset($feed_data['image_desc']);
				unset($feed_data['images']);
				
				if ($this->news->save($feed_data)) {
					foreach ($upload_data as $upload) {
						$image = array(
							'item_id'=>$feed_data['id'],
							'type' => 'news',
							'path' => $upload['file_name'],
							'width'=>$upload['image_width'],
							'height'=>$upload['image_height']
						);
						$this->image->save($image);
					}
								
					$this->session->set_flashdata('success','news is successfully added.');
				} else {
					$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
				}
				redirect(site_url('newsies'));
			} else {
				$data['error'] = $upload_data['error'];
			}
		}
		
		$content['content'] = $this->load->view('news/add',array(),true);
		$this->load->view('template',$content);
	}
	
	//retrieve
	function index()
	{
		$this->session->unset_userdata('searchterm');
		
		$pag = $this->config->item('pagination');
		$pag['base_url'] = site_url('newsies/index');
		$pag['total_rows'] = $this->news->count_all();
		
		$data['feeds'] = $this->news->get_all($pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('news/view',$data,true);		
		
		$this->load->view('template',$content);
	}
	
	function search()
	{
		$search_term = $this->searchterm_handler(array(
			"searchterm"=>$this->input->post('searchterm')
		));
		$data = $search_term;
		
		$pag = $this->config->item('pagination');
		
		$pag['base_url'] = site_url('newsies/search');
		$pag['total_rows'] = $this->news->count_all_by($search_term);
		
		$data['feeds'] = $this->news->get_all_by($search_term,$pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('news/search',$data,true);		
		$this->load->view('template',$content);
	}
	
	function searchterm_handler($searchterms = array())
	{
		$data = array();
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {
			foreach ($searchterms as $name=>$term) {
				if ($term && trim($term) != " ") {
					$this->session->set_userdata($name,$term);
					$data[$name] = $term;
				} else {
					$this->session->unset_userdata($term);
					$data[$name] = "";
				}
			}
		} else {
			foreach ($searchterms as $name=>$term) {
				if ($this->session->userdata($name)) {
					$data[$name] = $this->session->userdata($name);
				} else { 
					$data[$name] = "";
				}
			}
		}
		return $data;
	}
	
	//update
	function edit($feed_id=0)
	{
		$this->check_access('edit');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {
			if ($this->news->save($this->input->post(),$feed_id)) {
				$this->session->set_flashdata('success','news is successfully updated.');
			} else {
				$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
			}
			redirect(site_url('newsies'));
		}
		
		$data['news'] = $this->news->get_info($feed_id);
		
		$content['content'] = $this->load->view('news/edit',$data,true);		
		$this->load->view('template',$content);
	}
	
	function upload($feed_id=0)
	{
		$this->check_access('edit');
		
		$upload_data = $this->uploader->upload($_FILES);
		
		if (!isset($upload_data['error'])) {
			foreach ($upload_data as $upload) {
				$image = array(
								'item_id'=> $feed_id,
								'type' => 'news',
								'path' => $upload['file_name'],
								'width'=>$upload['image_width'],
								'height'=>$upload['image_height']
							);
				$this->image->save($image);
			}
		} else {
			$data['error'] = $upload_data['error'];
		}
		
		$data['news'] = $this->news->get_info($feed_id);
		
		$content['content'] = $this->load->view('news/edit',$data,true);		
		$this->load->view('template',$content);
	}
	
	function publish($id = 0)
	{
		$this->check_access('publish');
		
		$feed_data = array(
			'status'=> 1
		);
			
		if ($this->news->save($feed_data,$id)) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	function unpublish($id = 0)
	{
		$this->check_access('publish');
		
		$feed_data = array(
			'status'=> 0
		);
			
		if ($this->news->save($feed_data,$id)) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

	//delete
	function delete($feed_id=0)
	{
		$this->check_access('delete');
		
		if ($this->news->delete($feed_id)) {
			$this->session->set_flashdata('success','The news is successfully deleted.');
		} else {
			$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
		}
		redirect(site_url('newsies'));
	}
	
	
	//delete image
	function delete_image($feed_id,$image_id,$image_name)
	{
		$this->check_access('edit');
		
		if ($this->image->delete($image_id)) {
			unlink('./uploads/'.$image_name);
			$this->session->set_flashdata('success','The image is successfully deleted.');
		} else {
			$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
		}
		redirect(site_url('newsies/edit/'.$feed_id));
	}
}
?>