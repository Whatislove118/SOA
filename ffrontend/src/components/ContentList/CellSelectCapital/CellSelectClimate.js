import React, {useEffect, useState} from 'react';

export const CellSelectClimate = ({props, sendUpdate}) => {
    const [content, setContent] = useState(props);

    return (
        <select
            value={content}
            onChange={(event) => setContent(event.target.value)}
            onClick={() => {sendUpdate(content)}}
        >
            <option value=""> </option>
            <option value="MONSOON">MONSOON</option>
            <option value="HUMIDSUBTROPICAL">HUMIDSUBTROPICAL</option>
            <option value="SUBARCTIC">SUBARCTIC</option>
        </select>
    );
};
