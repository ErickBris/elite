
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li><a href="<?php echo site_url('djs');?>">Dj  List</a> <span class="divider"></span></li>
				<li>Add New </li>
			</ul>
			
			<?php
			$attributes = array('id' => 'feed-form','enctype' => 'multipart/form-data');
			echo form_open(site_url('djs/add'), $attributes);
			?>
				<legend>Dj Information</legend>
				
				<div class="row">
					<div class="col-sm-6">
							<div class="form-group">
								<label>Name</label>
								<input class="form-control" type="text" placeholder="Title" name='title' id='title'>
							</div>
							
							<div class="form-group">
								<label>Bio</label>
								<textarea class="form-control" name="description" placeholder="Description" rows="9"></textarea>
							</div>
					</div>
					
					<div class="col-sm-6">
							<div class="form-group">
								<label>Upload Photos</label>
								<input type="file" name="images1">
								<br/>
								
							</div>
					</div>
				</div>
				
				<hr/>
				
				<button type="submit" class="btn btn-primary">Submit</button>
				<a href="<?php echo site_url('djs');?>" class="btn">Cancel</a>
			</form>

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
							required: "Please fill title.",
							minlength: "The length of title must be greater than 4"
						}
					}
				});
			});
			</script>

