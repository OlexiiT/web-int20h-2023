import clsx from "clsx";

type Props = {
  label?: string;
  className?: string;
};

export default function SubmitButton({ label = "Submit", className }: Props) {
  return (
    <button className={clsx("bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded", className)}>
      {label}
    </button>
  );
}
