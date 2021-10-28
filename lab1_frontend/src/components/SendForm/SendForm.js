import {useState} from "react";
import Input from "../Input/Input";
import {Button} from "react-bootstrap";
import Select from "../Select/Select";
import SendButton from "../SendButton/SendButton";





const SendForm = (props) => {
    return (
        <div>
            <form className='send-form'>
                {props.formDescription.map(field => {
                    if (field.id === 'coordinate'){
                        return (
                            field.value.map(f => {
                            return <Input field={f} key={f.id} onChange={props.onChange}/>
                        }))
                    }else if (field.id === 'enum-climate' || field.id === 'enum-government'){
                      return <Select field={field} key={field.id} onChange={props.onChange}/>
                    }
                    return <Input field={field} key={field.id} onChange={props.onChange}/>
                })}
               <SendButton data={props.data} method={props.method} url={props.url}/>
            </form>
        </div>
    )
}



export default SendForm;
