import React from 'react';

const element = <h1> "Привет, мир" </h1>;
const City = (props) => {
    return (
        <div>
            {element}
            <input type={'number'} defaultValue={props.value} />
        </div>
    )
};

export default City;
