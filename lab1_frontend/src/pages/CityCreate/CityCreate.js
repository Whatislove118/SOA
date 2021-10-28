import {React, useState} from 'react';
import CityForm from '../../components/City/CityForm';
import SendForm from "../../components/SendForm/SendForm";


const CityCreate = () => {

    const [formDescription, setFormDescription] = useState([
        {id: 'name', type: 'text', placeholder: 'Enter name'},
        {id: 'coordinate', value: [
                {id: 'x', type: 'number', placeholder: 'Enter coordinate X', value: 0.0, step: 'any'},
                {id: 'y', type: 'number', placeholder: 'Enter coordinate Y', value: 0.0, step: 'any'},
         ]},
         {id: 'area', type: 'number', placeholder: 'Enter area'},
         {id: 'population', type: 'number', placeholder: 'Enter population'},
        {id: 'metersAboveSeaLevel', type: 'number', placeholder: 'Enter meters above sea level'},
        {id: 'establishmentDate', type: 'date', placeholder: 'Enter establishment date'},
        {id: 'enum-climate', placeholder: 'Enter climate type', value: ['MONSOON', 'HUMIDSUBTROPICAL', 'SUBARCTIC']},
        {id: 'enum-government', placeholder: 'Enter government type', value: ['ANARCHY', 'DESPOTISM', 'CORPORATOCRACY', 'MERITOCRACY', 'TOTALITARIANISM']},
        {id: 'governor-height', type: 'number', placeholder: 'Enter governor height cringe', value: 0.0, step: 'any'},
        {id: 'governor-birthday', type: 'text', placeholder: 'Enter governor birthday with time :/ cringe'},

    ])

    function changeFieldValue(event) {
        console.log(event.target.id, event.target.value)
        setFormDescription(formDescription.map(field => {
                if (field.id === event.target.id){
                    field.value = event.target.value
                }
                return field;
            })
        )
    }
    return (
        <div>
            <h2>City Create Form</h2>
             <SendForm formDescription={formDescription} onChange={changeFieldValue}/>
        </div>
    )


};

export default CityCreate;
