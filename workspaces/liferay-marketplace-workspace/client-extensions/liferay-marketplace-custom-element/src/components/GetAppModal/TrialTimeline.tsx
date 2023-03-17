import circleFill from '../../assets/icons/circle_fill.svg';
import radioSelected from '../../assets/icons/radio-button-checked-2.svg';
import timeline from '../../assets/images/timeline.png';

import './TrialTimeline.scss';

export function TrialTimeline() {
	return (
		<div className="get-app-modal-trial">
			<div className="get-app-modal-trial-timeline">
				<img
					alt="circle fill"
					className="get-app-modal-trial-timeline-icon-selected"
					src={radioSelected}
				/>

				<img
					alt="timeline"
					className="get-app-modal-trial-timeline-dash"
					src={timeline}
				/>

				<img
					alt="circleFill"
					className="get-app-modal-trial-timeline-icon"
					src={circleFill}
				/>
			</div>

			<div className="get-app-modal-trial-messages">
				<div className="get-app-modal-trial-messages-item">
					<div className="active title">
						Today - free trial for 30 days.{' '}
					</div>

					<div className="description">
						Start your free trial and test the full app for 30 days.
					</div>
				</div>

				<div className="get-app-modal-trial-messages-item">
					<div className="title">March 11</div>

					<div className="description">
						Your trial ends and this app will no longer function in
						your project, unless you've subscribed during the trial.
					</div>
				</div>
			</div>
		</div>
	);
}
