class App extends React.Component {
  constructor(props) {
      super(props);
      this.deleteEntry = this.deleteEntry.bind(this);
      this.createEntry = this.createEntry.bind(this);
      this.state = {
          entries: [],
          showForm: false
      };
   }
 
  componentDidMount() {
    this.loadEntries();
  }
  
  onClick(e){
	    e.preventDefault();
	    this.setState({showForm: !this.state.showForm})
  }
  
  loadEntries() {
      fetch('http://localhost:8080/entry/list') 
      .then((response) => response.json()) 
      .then((responseData) => { 
          this.setState({ 
        	  entries: responseData, 
          }); 
      });     
  } 
  
  deleteEntry(entry) {
      fetch ('http://localhost:8080/entry/'+entry.entryId,
      { method: 'POST',})
      .then( 
          res => this.loadEntries()
      )
      .catch( err => cosole.error(err))                
  }  
  
  createEntry(entry) {
      fetch('http://localhost:8080/entry/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(entry)
      })
      .then( 
          res => this.loadEntries()
      )
      .catch( err => cosole.error(err))
  }
  
  render() {
    return (
    	<section className="vbox">
	       <header className="card-header"> <p>Spring Boot + Hibernate + Reactjs demo project</p> </header>
	       <section className="panel">
		       <div className="form-control">
					<a href="#" className="btn btn-success float-right" onClick={this.onClick.bind(this)}>Create Entry</a>
				  {this.state.showForm && <EntryForm createEntry={this.createEntry}/> }
		          <EntryList deleteEntry={this.deleteEntry} entries={this.state.entries}/> 
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
	        <Entry key={entry.entryTitle} entry={entry} deleteEntry={this.props.deleteEntry}/>
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
    }

    deleteEntry() {
        this.props.deleteEntry(this.props.entry);
    } 
 
    render() {
        return (
        		<div className="card">
        		  <div className="card-header">
        		  {this.props.entry.entryTitle} <div className="float-right"><a href="#" className="btn btn-sm btn-danger" onClick={this.deleteEntry}>Delete Entry</a></div> 
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
        this.state = {entryTitle: '', entryDetail: ''};
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
        var newEntry = {entryTitle: this.state.entryTitle, entryDetail: this.state.entryDetail};
        this.props.createEntry(newEntry);        
    }
    
    render() {
        return (
    	     <form>
    	     <br/>
    	        <div className="form-group">
    	          <label htmlFor="formGroupExampleInput">Title</label>
    	          <input type="text" className="form-control"  name="entryTitle" onChange={this.handleChange}/> 
    	        </div>
    	        <div className="form-group">
    	          <label htmlFor="formGroupExampleInput2">Detail</label>
    	          <textarea className="form-control" name="entryDetail" onChange={this.handleChange}/>
    	        </div>
    	          <div className="col-md-2">
                  <a href="#" className="btn btn-success" onClick={this.handleSubmit}>Save</a>   
              </div> 
    	      </form>
         
        );
    }
}

ReactDOM.render(<App />, document.getElementById('root') );