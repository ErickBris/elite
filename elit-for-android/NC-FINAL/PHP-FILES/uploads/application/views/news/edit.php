
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li><a href="<?php echo site_url('newsies');?>">News</a> <span class="divider"></span></li>
				<li>Update News</li>
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
			echo form_open(site_url("newsies/edit/".$news->id), $attributes);
			?>
				<div class="row">
					<div class="col-sm-5">
						<div class="form-group">
							<label>Title</label>
							<input class="form-control" type="text" placeholder="title" name='title' id='title'
							 value="<?php echo $news->title;?>">
						</div>
						
						<div class="form-group">
							<label>Description</label>
							<textarea class="form-control" name="description" placeholder="Description" rows="9"><?php echo $news->description;?></textarea>
						</div>
						
						<input type="submit" value="Update" class="btn btn-primary"/>
						<a href="<?php echo site_url('newsies');?>" class="btn">Cancel</a>
					</div>
					
					<div class="col-sm-7">
						<b>Image Gallery</b> <a class="btn btn-primary btn-upload pull-right" data-toggle="modal" data-target="#uploadImage">Add Image</a>
						<hr/>			
						<?php
							$images = $this->image->get_all_by_type($news->id, 'news')->result();
							if(count($images) > 0):
						?>
							<div class="row">
							<?php
								$i= 0;
								foreach ($images as $img) {
									if ($i>0 && $i%3==0) {
										echo "</div><div class='row'>";
									}
									
									echo '<div class="col-md-4" style="height:100"><div class="thumbnail">'.
										'<img src="'.base_url('uploads/thumbs/'.$img->path).'"><br/>'.
										'<p class="text-center">'.
										'<a data-toggle="modal" data-target="#deletePhoto" class="delete-img" id="'.$img->id.'"   
											image="'.$img->path.'">Remove</a></p>'.
										'</div></div>';
								   $i++;
								}
							?>
							</div>
						
						<?php
							endif;
						?>
					</div>
				</div>
			</form>
			
			<div class="modal fade"  id="uploadImage">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">Upload Image</h4>
						</div>
						<?php
						$attributes = array('id' => 'upload-form','enctype' => 'multipart/form-data');
						echo form_open(site_url("newsies/upload/".$news->id), $attributes);
						?>
							<div class="modal-body">
								<div class="form-group">
									<label>Upload Photo</label>
									<input type="file" name="images1">
									<br/>
									
								</div>
							</div>
							<div class="modal-footer">
								<input type="submit" value="Upload" class="btn btn-primary"/>
								<a type="button" class="btn btn-default" data-dismiss="modal">Cancel</a>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal fade"  id="deletePhoto">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title">Deleting Image</h4>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete the photo?</p>
						</div>
						<div class="modal-footer">
							<a type="button" class="btn btn-default btn-delete-image">Yes</a>
							<a type="button" class="btn btn-default" data-dismiss="modal">Cancel</a>
						</div>
					</div>
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
							name:{
								required: "Please fill feed name.",
								minlength: "The length of feed name must be greater than 4"
							}
						}
					});
					
					$('.btn-upload').click(function(e){
						e.preventDefault();
					});
					
					$('.detail-img').click(function(e){
						e.preventDefault();
						var id = $(this).attr('id');
						var desc = $(this).attr('desc');
						var image = $(this).attr('image');
						var action = "<?php echo site_url("newsies/edit_image/".$news->id);?>";
						$('#image-form').attr('action', action + "/" + id);
						$('#image-form .edit_image_desc').val(desc);
						$('#image-form .image').attr('src',image);
					});
					
					$('.delete-img').click(function(e){
						e.preventDefault();
						var id = $(this).attr('id');
						var image = $(this).attr('image');
						var action = '<?php echo site_url('newsies/delete_image/'.$news->id);?>/' + id + '/' + image;
						$('.btn-delete-image').attr('href', action);
					});
				});
			</script>

