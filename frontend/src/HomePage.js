import React, { useState } from "react";
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { DateRange } from 'react-date-range';
import axios from "axios";
import "react-datepicker/dist/react-datepicker.css";

const HomePage = () => {
  const [prices, setPrices] = useState({});
  const [highestPrice, setHighestPrice] = useState(null);
  const [lowestPrice, setLowestPrice] = useState(null);
  const [state, setState] = useState([
    {
      startDate: new Date(),
      endDate: new Date(),
      key: 'selection'
    }
  ]);

  function getDateFormat(dateInput) {
    const date = new Date(dateInput);
    const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
    return formattedDate;
  }

  const handleApi = () => {
    axios
      .get("http://localhost:8080/api/price", {
        params: {
          startDate: getDateFormat(state[0].startDate),
          endDate: getDateFormat(state[0].endDate),
          currency: "USD"
        }
      })
      .then((response) => {
        setPrices(response.data);
        // Calculate highest and lowest prices
        const priceValues = Object.values(response.data);
        const highest = Math.max(...priceValues);
        const lowest = Math.min(...priceValues);
        setHighestPrice(highest);
        setLowestPrice(lowest);
      })
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Bitcoin Price Checker</h2>
      <DateRange
        editableDateInputs={true}
        onChange={item => setState([item.selection])}
        moveRangeOnFirstSelection={false}
        ranges={state}
      />
      <div className="text-center mt-4">
        <button onClick={handleApi} className="btn btn-primary">Check Price!</button>
      </div>

      {prices && Object.keys(prices).length > 0 && (
        <table className="table table-striped mt-4">
          <thead>
            <tr>
              <th>Date</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(prices).map(([date, price]) => (
              <tr key={date}>
                <td>{date}</td>
                <td>
                  {price === highestPrice ? 
                    <span className="text-success">{price} (high)</span> : 
                    price === lowestPrice ? 
                    <span className="text-danger">{price} (low)</span> : 
                    price}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default HomePage;