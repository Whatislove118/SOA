import React from "react";
import sendGet from '../../utils'

class CityGet extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            city: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({id: event.target.value});
       
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.id);
        let city = sendGet(this.state.id)
        console.log(city)
        this.setState({city: city})
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    ID:
                    <input id = 'id' type="number" value={this.state.id} onChange={this.handleChange} />
                </label>
                <hr/>
                <label>
                    Result:
                    <input id = 'result' type="text" value={this.state.city} />
                </label>
                <button type='submit'>Отправить запрос</button>
            </form>
        );
    }
}

export default CityGet;
