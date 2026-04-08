
import React, {useEffect, useState} from 'react';
import {Line} from 'react-chartjs-2';
import 'chart.js/auto';

export default function App(){
  const [live,setLive]=useState([]);
  const [history,setHistory]=useState([]);

  useEffect(()=>{
    fetch("http://localhost:8080/api/history")
      .then(res=>res.json())
      .then(setHistory);

    const ws = new WebSocket("ws://localhost:8080/ws/metrics");
    ws.onmessage = e=>{
      const val = parseFloat(e.data.split(":")[1]);
      setLive(prev=>[...prev.slice(-20), val]);
    };
  },[]);

  return (
    <div>
      <h1>🚀 Analytics Dashboard</h1>

      <h3>Live Data</h3>
      <Line data={{
        labels:live.map((_,i)=>i),
        datasets:[{label:"CPU Live", data:live}]
      }}/>

      <h3>Historical Data</h3>
      <Line data={{
        labels:history.map((_,i)=>i),
        datasets:[{label:"CPU History", data:history.map(h=>h.value)}]
      }}/>
    </div>
  )
}
