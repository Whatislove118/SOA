import React, {useState} from "react";
import Input from "../../components/Input/Input";
import SendForm from "../../components/SendForm/SendForm"






const Coordinates = () => {
    const [formDescription, setFormDescription] = useState([
        {id: 'x', type: 'number', placeholder: 'Enter X', value: 0.0, step: 'any'},
        {id: 'y', type: 'number', placeholder: 'Enter Y', value: 0.0, step: 'any'},
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

    return <div>
        <SendForm formDescription={formDescription} onChange={changeFieldValue}/>
        </div>
};
export default Coordinates;
