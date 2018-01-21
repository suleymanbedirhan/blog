class App extends React.Component {
  constructor(props) {
      super(props);
      this.deleteEntry = this.deleteEntry.bind(this);
      this.createEntry = this.createEntry.bind(this);
      this.state = {
          entries: [],
      };
   }
 
  componentDidMount() {
    this.loadEntries();
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
      fetch (entry._links.self.href,
      { method: 'DELETE',})
      .then( 
          res => this.loadEntries()
      )
      .catch( err => cosole.error(err))                
  }  
  
  createEntry(entry) {
      fetch('http://localhost:8080/entry/list', {
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
       <div>
          <EntryForm createEntry={this.createEntry}/>
          <EntryTable deleteEntry={this.deleteEntry} entries={this.state.entries}/> 
       </div>
    );
  }
}
    	
class EntryTable extends React.Component {
    constructor(props) {
        super(props);
    }
    
    render() {
    var entries = this.props.entries.map(entry =>
        <Entry key={entry.entryTitle} entry={entry} deleteEntry={this.props.deleteEntry}/>
    );

    return (
      <div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Firstname</th><th>Lastname</th><th> </th>
          </tr>
        </thead>
        <tbody>{entries}</tbody>
      </table>
      </div>);
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
          <tr>
            <td>{this.props.entry.entryTitle}</td>
            <td>{this.props.entry.entryDetail}</td>
            <td>
                <button className="btn btn-danger" onClick={this.deleteEntry}>Delete</button>
            </td>
          </tr>
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
        console.log("NAME: " + event.target.name + " VALUE: " + event.target.value)
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
            <div className="panel panel-default">
                <div className="panel-heading">Create Entry</div>
                <div className="panel-body">
                <form className="form-inline">
                    <div className="col-md-2">
                        <input type="text" placeholder="Entry Title" className="form-control"  name="entryTitle" onChange={this.handleChange}/>    
                    </div>
                    <div className="col-md-2">       
                        <input type="text" placeholder="Entry Detail" className="form-control" name="entryDetail" onChange={this.handleChange}/>
                    </div>
                    <div className="col-md-2">
                        <button className="btn btn-success" onClick={this.handleSubmit}>Save</button>   
                    </div>        
                </form>
                </div>      
            </div>
         
        );
    }
}

ReactDOM.render(<App />, document.getElementById('root') );