<?php

class Dtoken extends Club_Model
{
    protected $table_name;
	
	function __construct()
	{
		parent::__construct();
		$this->table_name = '76_dtoken';
	}
	
	function save(&$data,$id=false)
	{
		//if there is no data with this id, create new
		if (!$id && !$this->exists(array('id'=>$id))) {
			if ($this->db->insert($this->table_name,$data)) {
				$data['id'] = $this->db->insert_id();
				return true;
			}
		} else {
			//else update the data
			$this->db->where('id',$id);
			return $this->db->update($this->table_name,$data);
		}
		
		return $false;
	}
	

    function exists($data)
	{
		$this->db->from($this->table_name);
		
		if (isset($data['id'])) {
			$this->db->where('id',$data['id']);
		}
		
		if (isset($data['tokenid'])) {
			$this->db->where('tokenid',$data['tokenid']);
		}
		
		
		
		$query = $this->db->get();
		return ($query->num_rows()==1);
	}






}
	


?>