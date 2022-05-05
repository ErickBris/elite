<?php $this->load->view('panel/header');?>

<?php $this->load->view('panel/nav');?>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar teamps-sidebar">
			<?php $this->load->view('panel/sidebar');?>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="page-header">Hi, <?php echo $this->user->get_logged_in_user_info()->user_name;?>!</h2>
		 	
		 	<div class="row">
		  		<div class="col-sm-3 ">
		  			<a href="<?php  echo site_url('djs') ?>">
			  			
			  			
			  			<div class="hero-widget">
			  				<div class="icon">
			  					<i class="glyphicon glyphicon-headphones"></i>
			  				</div>
			  				<div class="text">
			  					
			  					<label class="text-muted">Djs
				  					<span class="badge badge-important">
			  				<?php echo $this->dj->count_all();?>
			  			</span>
				  					
			  					</label>
			  				</div>
			  			</div>
		  			</a>
		  		</div> 
			  	<div class="col-sm-3">
			  		<a href="<?php  echo site_url('newsies') ?>">
			  		
				  		
				  		
				  		<div class="hero-widget">
				  			<div class="icon">
				  				<i class="glyphicon glyphicon-pencil"></i>
				  			</div>
				  			<div class="text">
				  				<label class="text-muted">News
					  				
					  				<span class="badge badge-important">
				  			<?php echo $this->news->count_all();?>
				  		</span>
					  				
				  				</label>
				  			</div>
				  		</div>
			  		
			  		</a>
			  	</div>
			  	<div class="col-sm-3">
			  		<a href="<?php  echo site_url('mixes') ?>">
			  		
				  		
				  		
				  		<div class="hero-widget">
				  			
				  			<div class="icon">
				  				<i class="glyphicon glyphicon-volume-up"></i>
				  			</div>
				  			<div class="text">
				  				
				  				<label class="text-muted">Mix
					  				
					  				<span class="badge badge-important">
				  			<?php echo $this->mix->count_all();?>
				  		</span>
				  				</label>
				  			</div>
				  		</div>
			  		
			  		</a>
			  	</div>
			  	<div class="col-sm-3">
			  		<a href="<?php  echo site_url('events') ?>">
				  		
				  		
				  		
				  		<div class="hero-widget">
				  			<div class="icon">
				  				<i class="glyphicon glyphicon-calendar"></i>
				  			</div>
				  			<div class="text">
				  				
				  				<label class="text-muted">Club Events
					  				
					  				<span class="badge badge-important">
				  			<?php echo $this->event->count_all();?>
				  		</span>
				  				</label>
				  			</div>
				  		</div>
			  		</a>
			  	</div>
			  	
			  	
				
		  	</div>
			<hr/>
					  	
		  	<div class="row">
		  		<div class="col-md-6">
		  			<div class="panel panel-success">
		  				<div class="panel-heading clickable">
		  				
		  					<h3 class="panel-title">Latest Published mix</h3>
		  					<span class="pull-right "><i class="glyphicon glyphicon-minus"></i></span>
		  				</div>
		  				<table class='table table-condensed table-hover table-striped'>
		  					<?php 
		  						foreach($this->mix->get_all(3)->result() as $mix)
		  							echo "<tr><td>" .$mix->title.
		  								"</td></tr>";
		  					?>
		  					<tr>
		  						<td class="text-right"><a href='<?php echo site_url('mixes');?>'>View All</a></td>
		  					</tr>
		  				</table>
		  			</div>
		  		</div>
	       	
	          
	          <div class="col-md-6">
	          	<div class="panel panel-danger">
	          		<div class="panel-heading clickable">
	          			<h3 class="panel-title">Recent News</h3>
	          			<span class="pull-right "><i class="glyphicon glyphicon-minus"></i></span>
	          		</div>
	          		<table class='table table-condensed table-hover table-striped'>
	          		<?php 
	          			foreach($this->news->get_all(3)->result() as $news)
		  							echo "<tr><td>" .$news->title.
		  								"</td></tr>";
	          		?>
	          			<tr>
	          				<td class="text-right"><a href='<?php echo site_url('newsies');?>'>View All</a></td>
	          			</tr>
	          		</table>
	          	</div>
	          </div>
	          
	   	</div>
		   	
		   	<div class="row">
		   			<div class="col-md-6">
		   				<div class="panel panel-warning">
		   					<div class="panel-heading clickable">
		   						<h3 class="panel-title">Events</h3>
		   						<span class="pull-right "><i class="glyphicon glyphicon-minus"></i></span>
		   		     </div>
		   		     <table class='table table-condensed table-hover table-striped'>
		   		     <?php 
		   		     	foreach($this->event->get_all(3)->result() as $event)
		  							echo "<tr><td>" .$event->title.
		  								"</td></tr>";

		   		     ?>
			   		     <tr>
			   		     	<td class="text-right"><a href='<?php echo site_url('events');?>'>View All</a></td>
			   		     </tr>
		   		     </table>
		   			</div>
		   		</div>
		   		
		   		<div class="col-md-6">
		   			<div class="panel panel-info">
		   				<div class="panel-heading clickable">
		   					<h3 class="panel-title">Djs</h3>
		   					<span class="pull-right "><i class="glyphicon glyphicon-minus"></i></span>
		   				</div>
		   				<table class='table table-condensed table-hover table-striped'>
		   				<?php 
		   					foreach($this->dj->get_all(3)->result() as $dj)
		  							echo "<tr><td>" .$dj->title.
		  								"</td></tr>";
		   				?>
		   		    		<tr>
		   		    			<td class="text-right"><a href='<?php echo site_url('djs');?>'>View All</a></td>
		   		    		</tr>
		   				</table>
		   			</div>
		   		</div>
			     	
			 	</div>
			</div>
		</div>
	</div> 

<!--<?php $this->load->view('partial/footer');?>-->