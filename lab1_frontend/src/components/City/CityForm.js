import React from 'react';
import Coordinates from '../../pages/Coordinates/Coordinates';

class CityForm extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            coordinates: {
                x: '',
                y: ''
            },
            area: '',
            population: '',
            metersAboveSeaLevel: '',
            establishmentDate: '',
            climate: '',
            government: '',
            governor: {
                height: '',
                birthday: ''
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        if (event.target.id == 'x'){
            this.setState({x: event.target.value});
        }else{
            this.setState({y: event.target.value});
        }
       
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.x + ' ' + this.state.y);
        // event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Name:
                    <input id = 'name' type="text" value={this.state.name} onChange={this.handleChange}/>
                </label>
                <hr/>
                <br/>
                <label>
                    Coordinates:
                    <br/>
                    <label>
                        X:
                        <input id = 'x' type="number" value={this.state.coordinates.x} onChange={this.handleChange} />
                    </label>
                    <br/>
                    <label>
                        Y:
                        <input id = 'y' type="number" value={this.state.coordinates.y} onChange={this.handleChange} />
                    </label>    
                </label>  
                <hr/>
                <br/>              
                <label>
                    Area:
                    <input id = 'area' type="number" value={this.state.area} onChange={this.handleChange} />
                </label>                
                <br/>
                <hr/>
                <label>
                    Population:
                    <input id = 'population' type="number" value={this.state.population} onChange={this.handleChange} />
                </label>
                <br/>
                <hr/>
                <label>
                    MetersAboveSeaLevel:
                    <input id = 'metersAboveSeaLevel' type="number" value={this.state.metersAboveSeaLevel} onChange={this.handleChange} />
                </label>                
                <br/>
                <hr/>
                <label>
                    EstablishmentDate:
                    <input id = 'establishmentDate' type="date" value={this.state.establishmentDate} onChange={this.handleChange} />
                </label>                
                <br/>
                <hr/>
                <label>
                    Climate:
                    <input id = 'climate' type="str" value={this.state.climate} onChange={this.handleChange} />
                </label>                
                <br/>
                <hr/>
                <label>
                    Government:
                    <input id = 'government' type="number" value={this.state.government} onChange={this.handleChange} />
                </label>                
                <br/>
                <hr/>
                <label>
                    Governor:
                    <br/>
                    <label>
                        Height:
                        <input id = 'height' type="number" value={this.state.governor.height} onChange={this.handleChange} />
                    </label>
                    <br/>
                    <label>
                        Birthday:
                        <input id = 'birthday' type="date" value={this.state.governor.birthday} onChange={this.handleChange} />
                    </label>  
                </label>                
                <br/>
                <hr/>
                <button type='submit'>Отправить запрос</button>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

export default CityForm;
