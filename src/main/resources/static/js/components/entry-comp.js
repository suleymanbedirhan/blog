class App extends React.Component {
  constructor(props) {
      super(props);
      this.deleteEntry = this.deleteEntry.bind(this);
      this.createEntry = this.createEntry.bind(this);
      this.setEntry = this.setEntry.bind(this);
//      this.refresh = this.refresh.bind(this);
      this.state = {
          entries: [],
          showForm: false,
          entryTitle: '',
          entryDetail: ''
      };
   }
 
  componentDidMount() {
    this.loadEntries();
  }
  
  onClick(e){
	    e.preventDefault();
	    this.setState({
	    	showForm: !this.state.showForm,
	    	entryId: undefined,
	    	entryTitle: '',
	    	entryDetail: ''
	    	})
  }
  
  loadEntries() {
      fetch('http://localhost:8080/entry/list') 
      .then((response) => response.json()) 
      .then((responseData) => { 
    	if(responseData.length == 0){
    		this.setState({showForm: true})
    	}	  
        this.setState({ 
       	  entries: responseData
        }); 
      });     
  } 
  
  deleteEntry(entry) {
      fetch ('http://localhost:8080/entry/'+entry.entryId,
      { method: 'DELETE',})
      .then( 
          res => this.loadEntries()
      ).then(
		  this.state = {
	          entries: [],
	          showForm: false,
	          entryTitle: '',
	          entryDetail: ''
		      }	  
      )
      .catch( err => console.error(err))                
  }  
  
  createEntry(entry) {
      var mode ='';
      if(entry.entryId == null | entry.entryId == undefined){
    	  mode = 'add';
      }else{
    	  mode = 'update';
      }
	  fetch('http://localhost:8080/entry/' + mode, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(entry)
      })
      .then( 
          res => this.loadEntries()
      ).then(
    	  this.setState({showForm: !this.state.showForm}) 	  
      )
      .catch( err => console.error(err))
  }
  
  setEntry(entry) {
	  this.setState({
		  showForm: !this.state.showForm,
		  entryId: entry.entryId,
		  entryTitle: entry.entryTitle,
		  entryDetail: entry.entryDetail
		  })
	  }
  
  render() {
    return (
    	<section className="vbox">
	       <header className="card-header"> <p>Spring Boot + Hibernate + Reactjs demo project</p> </header>
	       <section className="panel">
		       <div className="form-control">
				  <a href="#" className="btn btn-success float-right" onClick={this.onClick.bind(this)}>Create Entry</a>
				  {this.state.showForm && <EntryForm createEntry={this.createEntry} setEntryForm={this.state}/> }
		          <EntryList deleteEntry={this.deleteEntry} setEntry={this.setEntry} entries={this.state.entries}/> 
		       </div>
		   </section>
	   </section>
    );
  }
}
    	
class EntryList extends React.Component {
    constructor(props) {
        super(props);
    }
    
    render() {
	    var entries = this.props.entries.map(entry =>
	        <Entry key={entry.entryTitle} entry={entry} deleteEntry={this.props.deleteEntry} setEntry={this.props.setEntry}/>
	    );
	
	    return (
	      <form className="panel">
	    	<br/>
	    	<br/>
		      <div className="panel">
		      	<div className="panel-body">
		      		{entries}
			    </div>
		      </div>
		    <br/>
	      </form>
	      );
  }
}
        
class Entry extends React.Component {
    constructor(props) {
        super(props);
        this.deleteEntry = this.deleteEntry.bind(this);
        this.setEntry = this.setEntry.bind(this);
    }

    deleteEntry() {
        this.props.deleteEntry(this.props.entry);
    } 
    
    setEntry() {
    	console.log("props entry:" + this.props.entry.entryId);
        this.props.setEntry(this.props.entry);
    } 
 
    render() {
        return (
        		<div className="card">
        		  <div className="card-header">
        		  	{this.props.entry.entryTitle} 
        		  	<div className="float-right">
        		  		<a href="#" className="btn btn-sm-4-offset btn-link" onClick={this.setEntry}>Update</a>
        		  		<a href="#" className="btn btn-sm-4-offset btn-danger" onClick={this.deleteEntry}>Delete</a>
        		  	</div> 
        		  </div>
        		  <div className="card-block">
        		    <blockquote className="card-blockquote">
        		      <footer>Someone famous in <cite title="Source Title">{this.props.entry.entryDetail}</cite> </footer>
        		    </blockquote>
        		  </div>
        		</div>
        );
    } 
}

class EntryForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {entryId: this.props.setEntryForm.entryId,
        			  entryTitle: this.props.setEntryForm.entryTitle, 
        			  entryDetail: this.props.setEntryForm.entryDetail};
        this.handleSubmit = this.handleSubmit.bind(this);   
        this.handleChange = this.handleChange.bind(this);     
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }    
    
    handleSubmit(event) {
        event.preventDefault();
        var newEntry = {entryId: this.state.entryId, entryTitle: this.state.entryTitle, entryDetail: this.state.entryDetail};
        this.props.createEntry(newEntry);
        
    }
    
    render() {
        return (
    	     <form>
    	     <br/>
    	        <div className="form-group">
    	          <label htmlFor="formGroupExampleInput">Title</label>
    	          <input type="text" className="form-control" name="entryTitle" onChange={this.handleChange} value={this.state.entryTitle}/> 
    	        </div>
    	        <div className="form-group">
    	          <label htmlFor="formGroupExampleInput2">Detail</label>
    	          <textarea className="form-control" name="entryDetail" onChange={this.handleChange} value={this.state.entryDetail}/>
    	        </div>
    	          <div className="col-md-2">
                  <a href="#" className="btn btn-success" onClick={this.handleSubmit}>Save</a>   
              </div> 
    	      </form>
         
        );
    }
}

ReactDOM.render(<App />, document.getElementById('root') );
