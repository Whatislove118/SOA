import React, {useState} from 'react';

export const CellSelectorGovernment = ({props, sendUpdate}) => {
    const [content, setContent] = useState(props);

    return (
        <select
            value={content}
            onChange={(event) => setContent(event.target.value)}
            onClick={() => {sendUpdate(content)}}
        >
            <option value=""> </option>
            <option value="ANARCHY">ANARCHY</option>
            <option value="DESPOTISM">DESPOTISM</option>
            <option value="CORPORATOCRACY">CORPORATOCRACY</option>
            <option value="MERITOCRACY">MERITOCRACY</option>
            <option value="TOTALITARIANISM">TOTALITARIANISM</option>
        </select>
    );
};

