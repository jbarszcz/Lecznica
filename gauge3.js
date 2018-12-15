var tempGauge3;

function changeGauge3(Stopic,gauge) {
	    // by @jpmens, Sep 2013
	    // from @bordignons Sep 2013
	    // original idea.. http://www.desert-home.com/2013/06/how-to-use-steelseries-gauges-with.html
	    // with help.. http://harmoniccode.blogspot.com.au/
        // and code.. https://github.com/HanSolo/SteelSeries-Canvas
        
        this.Stopic = Stopic;
        console.log(Stopic);
  
        var general_gauge = document.getElementById(gauge);
        console.log("####" + general_gauge);

		tempGauge2 = new steelseries.Radial(general_gauge, {
			gaugeType: steelseries.GaugeType.TYPE4,
			minValue:-15,
			maxValue:50,
			size: 400,
			frameDesign: steelseries.FrameDesign.STEEL,
			knobStyle: steelseries.KnobStyle.STEEL,
			pointerType: steelseries.PointerType.TYPE6,
			lcdDecimals: 0,
			section: null,
			area: null,
			titleString: Stopic,
			unitString: 'C',
			threshold: 100,
			lcdVisible: true,
			lcdDecimals: 2
		   });
		tempGauge3.setValue(''); //gives a blank display 'NaN' until broker has connected
		tempGauge3.setLedColor(steelseries.LedColor.RED_LED); //set the LED RED until connected
		/* Connect to MQTT broker */
		//client.connect(options);
	}