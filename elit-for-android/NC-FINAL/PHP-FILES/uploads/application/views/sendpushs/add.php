           <ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li><a href="<?php echo site_url('sendpushs');?>">Push</a> <span class="divider"></span></li>
				<li>Send push</li>
			</ul>
			
			<!-- Message -->
			<?php if($this->session->flashdata('success')): ?>
				<div class="alert alert-success fade in">
					<?php echo $this->session->flashdata('success');?>
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				</div>
			<?php elseif($this->session->flashdata('error')):?>
				<div class="alert alert-danger fade in">
					<?php echo $this->session->flashdata('error');?>
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				</div>
			<?php endif;?>
			
			<?php
			$attributes = array('id' => 'feed-form','enctype' => 'multipart/form-data');
			echo form_open(site_url('sendpushs/add'), $attributes);
			?>
			
			<div class="row">
			<div class="col-sm-5">
			      
			      
			            <div class="form-group">
							<label>Name / Company</label>
							<input class="form-control" type="text" placeholder="title" name='title' id='name'
							 value=" ">
						</div>
						
						<input type="submit" value="Send" class="btn btn-primary"/>
						<a href="<?php echo site_url('sendpushs');?>" class="btn">Cancel</a>
			
			</div>
			</div>
			
			<script>
			$(document).ready(function(){
				$('#feed-form').validate({
					rules:{
						title:{
							required: true,
							minlength: 4
						}
					},
					messages:{
						title:{
							required: "Please fill message.",
							minlength: "The length of title must be greater than 4"
						}
					}
				});
			});
			</script>