import {useState} from "react";
import Input from "../../components/Input/Input";
import SendForm from "../../components/SendForm/SendForm";
import {backUrl} from "../../App";


const CityGet = () => {
    const [formDescription, setFormDescription] = useState([
        {id: 'id', type: 'number', placeholder: 'Enter city ID'}
    ])

    const [data, setData] = useState({id: 0})

    function changeFieldValue(event, id) {
        setFormDescription(formDescription.map(field => {
                if (field.id === event.target.id){
                    field.value = event.target.value
                }
                return field;
            })
        )
        setData(formDescription.map(field => {
            data.id = field.value
            return data
        }))
    }

    return (
        <div>
            <SendForm formDescription={formDescription} onChange={changeFieldValue} data={data} method={'get'} url={backUrl}/>
        </div>
    )
}




export default CityGet;
